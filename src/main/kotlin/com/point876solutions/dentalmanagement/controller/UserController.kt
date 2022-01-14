package com.point876solutions.dentalmanagement.controller


import com.point876solutions.dentalmanagement.constants.FileConstant.*
import com.point876solutions.dentalmanagement.constants.SecurityConstants.JWT_TOKEN_HEADER
import com.point876solutions.dentalmanagement.exception.ExceptionHandling
import com.point876solutions.dentalmanagement.exception.domain.*
import com.point876solutions.dentalmanagement.models.HttpResponse
import com.point876solutions.dentalmanagement.models.User
import com.point876solutions.dentalmanagement.models.UserPrincipal
import com.point876solutions.dentalmanagement.service.UserService
import com.point876solutions.dentalmanagement.utility.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.lang.Boolean
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.mail.MessagingException
import kotlin.ByteArray
import kotlin.Int
import kotlin.String
import kotlin.Throws
import kotlin.also
import kotlin.toString

@RestController
@RequestMapping(path = ["/", "/user"])
class UserController @Autowired constructor(
    authenticationManager: AuthenticationManager,
    userService: UserService,
    jwtTokenProvider: JwtTokenProvider
) : ExceptionHandling() {
    private val authenticationManager: AuthenticationManager
    private val userService: UserService
    private val jwtTokenProvider: JwtTokenProvider

    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseEntity<User> {
        authenticate(user.getUsername().toString(), user.getPassword().toString())
        val loginUser: User = userService.findUserByUsername(user.getUsername())!!
        val userPrincipal = UserPrincipal(loginUser)
        val jwtHeader: HttpHeaders = getJwtHeader(userPrincipal)
        return ResponseEntity<User>(loginUser, jwtHeader, HttpStatus.OK)


    }

    @PostMapping("/register")
    @Throws(
        UserNotFoundException::class,
        UsernameExistException::class,
        EmailExistException::class,
        MessagingException::class
    )
    fun register(@RequestBody user: User): ResponseEntity<User> {

        val newUser: User =
            userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail())!!
        return ResponseEntity<User>(newUser, HttpStatus.OK)
    }

    @PostMapping("/add")
    @Throws(
        UserNotFoundException::class,
        UsernameExistException::class,
        EmailExistException::class,
        IOException::class,
        NotAnImageFileException::class
    )
    fun addNewUser(
        @RequestParam("firstName") firstName: String?,
        @RequestParam("lastName") lastName: String?,
        @RequestParam("username") username: String?,
        @RequestParam("email") email: String?,
        @RequestParam("role") role: String?,
        @RequestParam("isActive") isActive: String?,
        @RequestParam("isNonLocked") isNonLocked: String?,
        @RequestParam(value = "profileImage", required = false) profileImage: MultipartFile?
    ): ResponseEntity<User> {
        val newUser: User = userService.addNewUser(
            firstName,
            lastName,
            username,
            email,
            role,
            Boolean.parseBoolean(isNonLocked),
            Boolean.parseBoolean(isActive),
            profileImage
        )!!
        return ResponseEntity<User>(newUser, HttpStatus.OK)
    }

    @PostMapping("/update")
    @Throws(
        UserNotFoundException::class,
        UsernameExistException::class,
        EmailExistException::class,
        IOException::class,
        NotAnImageFileException::class
    )
    fun update(
        @RequestParam("currentUsername") currentUsername: String?,
        @RequestParam("firstName") firstName: String?,
        @RequestParam("lastName") lastName: String?,
        @RequestParam("username") username: String?,
        @RequestParam("email") email: String?,
        @RequestParam("role") role: String?,
        @RequestParam("isActive") isActive: String?,
        @RequestParam("isNonLocked") isNonLocked: String?,
        @RequestParam(value = "profileImage", required = false) profileImage: MultipartFile?
    ): ResponseEntity<User> {
        val updatedUser: User = userService.updateUser(
            currentUsername,
            firstName,
            lastName,
            username,
            email,
            role,
            Boolean.parseBoolean(isNonLocked),
            Boolean.parseBoolean(isActive),
            profileImage
        )!!
        return ResponseEntity<User>(updatedUser, HttpStatus.OK)
    }

    @GetMapping("/find/{username}")
    fun getUser(@PathVariable("username") username: String?): ResponseEntity<User> {
        val user: User = userService.findUserByUsername(username)!!
        return ResponseEntity<User>(user, HttpStatus.OK)
    }

    @GetMapping("/list")
    fun getAllUsers(): ResponseEntity<List<User>>? {
        val users = userService.getUsers()
        return ResponseEntity(users, HttpStatus.OK)
    }

    @GetMapping("/resetpassword/{email}")
    @Throws(MessagingException::class, EmailNotFoundException::class)
    fun resetPassword(@PathVariable("email") email: String): ResponseEntity<HttpResponse> {
        userService.resetPassword(email)
        return response(HttpStatus.OK, EMAIL_SENT + email)
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    @Throws(IOException::class)
    fun deleteUser(@PathVariable("username") username: String?): ResponseEntity<HttpResponse> {
        userService.deleteUser(username)
        return response(HttpStatus.OK, USER_DELETED_SUCCESSFULLY)
    }

    @PostMapping("/updateProfileImage")
    @Throws(
        UserNotFoundException::class,
        UsernameExistException::class,
        EmailExistException::class,
        IOException::class,
        NotAnImageFileException::class
    )
    fun updateProfileImage(
        @RequestParam("username") username: String?,
        @RequestParam(value = "profileImage") profileImage: MultipartFile?
    ): ResponseEntity<User> {
        val user: User = userService.updateProfileImage(username, profileImage)!!
        return ResponseEntity<User>(user, HttpStatus.OK)
    }

    @GetMapping(path = ["/image/{username}/{fileName}"], produces = [MediaType.IMAGE_JPEG_VALUE])
    @Throws(
        IOException::class
    )
    fun getProfileImage(
        @PathVariable("username") username: String,
        @PathVariable("fileName") fileName: String
    ): ByteArray {
        return Files.readAllBytes(Paths.get(USER_FOLDER.toString() + username + FORWARD_SLASH + fileName))
    }

    @GetMapping(path = ["/image/profile/{username}"], produces = [MediaType.IMAGE_JPEG_VALUE])
    @Throws(
        IOException::class
    )
    fun getTempProfileImage(@PathVariable("username") username: String): ByteArray {
        val url = URL(TEMP_PROFILE_IMAGE_BASE_URL.toString() + username)
        val byteArrayOutputStream = ByteArrayOutputStream()
        url.openStream().use { inputStream ->
            var bytesRead: Int
            val chunk = ByteArray(1024)
            while (inputStream.read(chunk).also { bytesRead = it } > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead)
            }
        }
        return byteArrayOutputStream.toByteArray()
    }

    private fun response(httpStatus: HttpStatus, message: String): ResponseEntity<HttpResponse> {
        return ResponseEntity<HttpResponse>(
            HttpResponse(
                httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().uppercase(Locale.getDefault()),
                message
            ), httpStatus
        )
    }

    private fun getJwtHeader(user: UserPrincipal): HttpHeaders {
        val headers = HttpHeaders()
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user))
        return headers
    }

    private fun authenticate(username: String, password: String) {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
    }

    companion object {
        const val EMAIL_SENT = "An email with a new password was sent to: "
        const val USER_DELETED_SUCCESSFULLY = "User deleted successfully"
    }

    init {
        this.authenticationManager = authenticationManager
        this.userService = userService
        this.jwtTokenProvider = jwtTokenProvider
    }
}