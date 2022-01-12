package com.point876solutions.dentalmanagement.repository

import com.point876solutions.dentalmanagement.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    
    fun findUserByUsername(username: String): User?

    fun findUserByEmail(email: String): User?
}