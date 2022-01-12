package com.point876solutions.dentalmanagement.service.ServiceImpl

import com.point876solutions.dentalmanagement.constants.FileConstant.*
import com.point876solutions.dentalmanagement.constants.UserImplConstants.EMAIL_ALREADY_EXISTS
import com.point876solutions.dentalmanagement.constants.UserImplConstants.FOUND_USER_BY_USERNAME
import com.point876solutions.dentalmanagement.constants.UserImplConstants.NO_USER_FOUND_BY_EMAIL
import com.point876solutions.dentalmanagement.constants.UserImplConstants.NO_USER_FOUND_BY_USERNAME
import com.point876solutions.dentalmanagement.constants.UserImplConstants.USERNAME_ALREADY_EXISTS
import com.point876solutions.dentalmanagement.exception.domain.*
import com.point876solutions.dentalmanagement.models.Enum.Role
import com.point876solutions.dentalmanagement.models.User
import com.point876solutions.dentalmanagement.models.UserPrincipal
import com.point876solutions.dentalmanagement.repository.UserRepository
import com.point876solutions.dentalmanagement.service.EmailService
import com.point876solutions.dentalmanagement.service.UserService
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.StringUtils
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
@Qualifier("userDetailsService")
class UserServiceImpl: UserService, UserDetailsService {

    private val LOGGER = LoggerFactory.getLogger(javaClass)

    @Autowired
    private val userRepository: UserRepository? = null

    private val passwordEncoder: BCryptPasswordEncoder? = BCryptPasswordEncoder()

    @Autowired
    private val loginAttemptService: LoginAttemptService? = null

    @Autowired
    private val emailService: EmailService? = null

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User? = userRepository?.findUserByUsername(username)
        return if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME.toString() + username)
            throw UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME.toString() + username)
        } else {
            validateLoginAttempt(user)
            user.setLastLoginDateDisplay(user.getLastLoginDate())
            user.setLastLoginDate(Date())
            userRepository?.save(user)
            val userPrincipal = UserPrincipal(user)
            LOGGER.info(FOUND_USER_BY_USERNAME.toString() + username)
            userPrincipal
        }
    }

    override fun register(firstName: String?, lastName: String?, username: String?, email: String?): User? {


        //validateNewUsernameAndEmail(StringUtils.EMPTY, username!!, email!!)
        val user = User()
        user.setUserId(generateUserId())
        println(user.getUserID())
        val password: String = generatePassword()
        user.setFirstName(firstName)
        println(user.getFirstName())
        user.setLastName(lastName)
        println(user.getLastName())
        user.setUsername(username)
        println(user.getUsername())
        user.setEmail(email)
        println(user.getEmail())
        user.setJoinDate(Date())
        println(user.getJoinDate())
        user.setPassword(encodePassword(password))
        println(user.getPassword())
        user.setActive(true)
        println(user.getIsActive())
        user.setNotLocked(true)
        println(user.getIsNotLocked())
        user.setRole(Role.ROLE_SUPER_ADMIN.name)
        println(user.getRoles())
        user.setAuthorities(Role.ROLE_SUPER_ADMIN.authorities)
        println(user.getAuthorities())
        user.setProfileImageUrl("https://cdn3.vectorstock.com/i/1000x1000/20/92/user-man-icon-vector-25482092.jpg")
        println(user.getProfileImageUrl())
        //user.setProfileImageUrl(getTemporaryProfileImageUrl(username))

        println(user.getProfileImageUrl())

        userRepository!!.save(user)
        LOGGER.info("New user password: $password")
        //emailService?.sendNewPasswordEmail(firstName.toString(), password, email.toString())
        return user
    }

    override fun getUsers(): List<User> {
        return userRepository!!.findAll()
    }

    override fun findUserByUsername(username: String?): User? {
        return userRepository!!.findUserByUsername(username.toString())

    }

    override fun findUserByEmail(email: String?): User? {
        return userRepository!!.findUserByEmail(email.toString())
    }

    override fun addNewUser(
        firstName: String?,
        lastName: String?,
        username: String?,
        email: String?,
        role: String?,
        isNonLocked: Boolean,
        isActive: Boolean,
        profileImage: MultipartFile?
    ): User? {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email)
        val user = User()
        val password = generatePassword()
        user.setUserId(generateUserId())
        user.setFirstName(firstName)
        user.setLastName(lastName)
        user.setJoinDate(Date())
        user.setUsername(username!!)
        user.setEmail(email!!)
        user.setPassword(encodePassword(password))
        user.setActive(isActive)
        user.setNotLocked(isNonLocked)
        user.setRole(getRoleEnumName(role!!).name)
        user.setAuthorities(getRoleEnumName(role!!).authorities)
        user.setProfileImageUrl(getTemporaryProfileImageUrl(username!!))
        userRepository!!.save(user)
        saveProfileImage(user, profileImage)
        LOGGER.info("New user password: $password")
        return user
    }

    override fun updateUser(
        currentUsername: String?,
        newFirstName: String?,
        newLastName: String?,
        newUsername: String?,
        newEmail: String?,
        role: String?,
        isNonLocked: Boolean,
        isActive: Boolean,
        profileImage: MultipartFile?
    ): User? {
        val currentUser = validateNewUsernameAndEmail(currentUsername!!, newUsername, newEmail)
        currentUser!!.setFirstName(newFirstName)
        currentUser!!.setLastName(newLastName)
        currentUser!!.setUsername(newUsername!!)
        currentUser!!.setEmail(newEmail!!)
        currentUser!!.setActive(isActive)
        currentUser!!.setNotLocked(isNonLocked)
        currentUser!!.setRole(getRoleEnumName(role.toString()).name)
        currentUser!!.setAuthorities(getRoleEnumName(role.toString()).authorities)
        userRepository!!.save(currentUser)
        saveProfileImage(currentUser!!, profileImage)
        return currentUser
    }

    override fun deleteUser(username: String?) {
        val user = userRepository!!.findUserByUsername(username!!)
        val userFolder: Path = Paths.get(USER_FOLDER + user!!.getUsername()).toAbsolutePath().normalize()
        FileUtils.deleteDirectory(File(userFolder.toString()))
        userRepository!!.deleteById(user!!.getId()!!)
    }

    override fun resetPassword(email: String?) {
        val user = userRepository!!.findUserByEmail(email!!)
            ?: throw EmailNotFoundException(NO_USER_FOUND_BY_EMAIL.toString() + email)
        val password = generatePassword()
        user.setPassword(encodePassword(password))
        userRepository!!.save(user)
        LOGGER.info("New user password: $password")
        emailService!!.sendNewPasswordEmail(user.getFirstName()!!, password, user.getEmail()!!)
    }

    override fun updateProfileImage(username: String?, profileImage: MultipartFile?): User? {
        val user = validateNewUsernameAndEmail(username!!, null, null)
        if (user != null) {
            saveProfileImage(user, profileImage)
        }
        return user
    }


    private fun validateLoginAttempt(user: User) {
        if (user.getIsNotLocked()) {
            if (loginAttemptService!!.hasExceededMaxAttempts(user.getUsername().toString())) {
                user.setNotLocked(false)
            } else {
                user.setNotLocked(true)
            }
        } else {
            loginAttemptService!!.evictUserFromLoginAttemptCache(user.getUsername())
        }
    }


    @Throws(UserNotFoundException::class, UsernameExistException::class, EmailExistException::class)
    private fun validateNewUsernameAndEmail(
        currentUsername: String,
        newUsername: String?,
        newEmail: String?
    ): User? {
        val userByNewUsername = findUserByUsername(newUsername)
        val userByNewEmail = findUserByEmail(newEmail)
        return if (StringUtils.isNotBlank(currentUsername)) {
            val currentUser = findUserByUsername(currentUsername)
                ?: throw UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername)
            if (userByNewUsername != null && !currentUser.getId()!!.equals(userByNewUsername.getId())) {
                throw UsernameExistException(USERNAME_ALREADY_EXISTS)
            }
            if (userByNewEmail != null && !currentUser.getId()!!.equals(userByNewEmail.getId())) {
                throw EmailExistException(EMAIL_ALREADY_EXISTS)
            }
            currentUser
        } else {
            if (userByNewUsername != null) {
                throw UsernameExistException(USERNAME_ALREADY_EXISTS)
            }
            if (userByNewEmail != null) {
                throw EmailExistException(EMAIL_ALREADY_EXISTS)
            }
            null
        }
    }

    private fun generatePassword(): String {
        return RandomStringUtils.randomAlphanumeric(10)
    }

    private fun encodePassword(password: String): String {
        println("Password encoder entered")
        return passwordEncoder!!.encode(password)
    }

    private fun generateUserId(): String {
        return RandomStringUtils.randomNumeric(10)
    }

    private fun getTemporaryProfileImageUrl(username: String): String {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH.toString() + username)
            .toUriString()
    }


    @Throws(IOException::class, NotAnImageFileException::class)
    private fun saveProfileImage(user: User, profileImage: MultipartFile?) {
        if (profileImage != null) {
            if (!Arrays.asList(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE)
                    .contains(profileImage.contentType)
            ) {
                throw NotAnImageFileException(profileImage.originalFilename + NOT_AN_IMAGE_FILE)
            }
            val userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize()
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder)
                LOGGER.info(DIRECTORY_CREATED + userFolder)
            }
            Files.deleteIfExists(Paths.get(USER_FOLDER + user.getUsername() + DOT + JPG_EXTENSION))
            Files.copy(
                profileImage.inputStream,
                userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION),
                StandardCopyOption.REPLACE_EXISTING
            )
            user.setProfileImageUrl(setProfileImageUrl(user.getUsername())!!)
            userRepository!!.save(user)
            LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM.toString() + profileImage.originalFilename)
        }
    }

    private fun setProfileImageUrl(username: String?): String? {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(
            USER_IMAGE_PATH.toString() + username + FORWARD_SLASH
                    + username + DOT + JPG_EXTENSION
        ).toUriString()
    }

    private  fun getRoleEnumName(role: String): Role {
        return Role.valueOf(role.uppercase(Locale.getDefault()))
    }
}