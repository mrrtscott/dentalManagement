package com.point876solutions.dentalmanagement.listener


import org.springframework.beans.factory.annotation.Autowired
import com.point876solutions.dentalmanagement.service.ServiceImpl.LoginAttemptService
import org.springframework.context.event.EventListener
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent
import org.springframework.stereotype.Component

@Component
class AuthenticationFailureListener @Autowired constructor(private val loginAttemptService: LoginAttemptService) {
    @EventListener
    fun onAuthenticationFailure(event: AuthenticationFailureBadCredentialsEvent) {
        val principal = event.authentication.principal
        if (principal is String) {
            val username = event.authentication.principal as String
            loginAttemptService.addUserToLoginAttemptCache(username)
        }
    }
}