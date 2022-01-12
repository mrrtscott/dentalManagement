package com.point876solutions.dentalmanagement.service

import com.point876solutions.dentalmanagement.exception.domain.*
import com.point876solutions.dentalmanagement.models.User
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import javax.mail.MessagingException

interface UserService {

    @Throws(
        UserNotFoundException::class,
        UsernameExistException::class,
        EmailExistException::class,
        MessagingException::class
    )
    fun register(firstName: String?, lastName: String?, username: String?, email: String?): User?

    fun getUsers(): List<User>

    fun findUserByUsername(username: String?): User?

    fun findUserByEmail(email: String?): User?
    @Throws(
        UserNotFoundException::class,
        UsernameExistException::class,
        EmailExistException::class,
        IOException::class,
        NotAnImageFileException::class
    )
    fun addNewUser(
        firstName: String?,
        lastName: String?,
        username: String?,
        email: String?,
        role: String?,
        isNonLocked: Boolean,
        isActive: Boolean,
        profileImage: MultipartFile?
    ): User?

    @Throws(
        UserNotFoundException::class,
        UsernameExistException::class,
        EmailExistException::class,
        IOException::class,
        NotAnImageFileException::class
    )
    fun updateUser(
        currentUsername: String?,
        newFirstName: String?,
        newLastName: String?,
        newUsername: String?,
        newEmail: String?,
        role: String?,
        isNonLocked: Boolean,
        isActive: Boolean,
        profileImage: MultipartFile?
    ): User?

    @Throws(IOException::class)
    fun deleteUser(username: String?)

    @Throws(MessagingException::class, EmailNotFoundException::class)
    fun resetPassword(email: String?)

    @Throws(
        UserNotFoundException::class,
        UsernameExistException::class,
        EmailExistException::class,
        IOException::class,
        NotAnImageFileException::class
    )
    fun updateProfileImage(username: String?, profileImage: MultipartFile?): User?




}