package com.point876solutions.dentalmanagement.constants

object Authority {
    val USER_AUTHORITIES = arrayOf("user:read")
    val HR_AUTHORITIES = arrayOf("user:read", "user:update")
    val MANAGER_AUTHORITIES = arrayOf("user:read", "user:update")
    val ADMIN_AUTHORITIES = arrayOf("user:read", "user:create", "user:update")
    val SUPER_ADMIN_AUTHORITIES = arrayOf("user:read", "user:create", "user:update", "user:delete")
}