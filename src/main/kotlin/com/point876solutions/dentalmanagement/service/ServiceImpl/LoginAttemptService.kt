package com.point876solutions.dentalmanagement.service.ServiceImpl

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

@Service
class LoginAttemptService {

    private var loginAttemptCache: LoadingCache<String, Int>? = null

    constructor() {
        loginAttemptCache = CacheBuilder.newBuilder().expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(100).build(object : CacheLoader<String, Int>() {
                override fun load(p0: String): Int {
                    return 0
                }
            })
    }


    fun evictUserFromLoginAttemptCache(username: String?) {
        loginAttemptCache?.invalidate(username!!)
    }







    fun addUserToLoginAttemptCache(username: String) {
        var attempts = 0
        try {
            attempts = ATTEMPT_INCREMENT + loginAttemptCache!![username]
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
        loginAttemptCache?.put(username, attempts)
    }

    fun hasExceededMaxAttempts(username: String): Boolean {
        try {
            return loginAttemptCache!![username] >= MAXIMUM_NUMBER_OF_ATTEMPTS
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
        return false
    }

    companion object {
        private const val MAXIMUM_NUMBER_OF_ATTEMPTS = 5
        private const val ATTEMPT_INCREMENT = 1
    }


}