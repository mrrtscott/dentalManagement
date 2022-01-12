package com.point876solutions.dentalmanagement.listener

import com.point876solutions.dentalmanagement.models.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import com.point876solutions.dentalmanagement.service.ServiceImpl.LoginAttemptService
import org.springframework.context.event.EventListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.stereotype.Component

@Component
class AuthenticationSuccessListener @Autowired constructor(private val loginAttemptService: LoginAttemptService) {
    @EventListener
    fun onAuthenticationSuccess(event: AuthenticationSuccessEvent) {
        val principal = event.authentication.principal
        if (principal is UserPrincipal) {
            val user = event.authentication.principal as UserPrincipal
            loginAttemptService.evictUserFromLoginAttemptCache(user.username)
        }
    }
}