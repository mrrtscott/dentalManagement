package com.point876solutions.dentalmanagement.constants

object SecurityConstants {

    const val EXPIRATION_TIME: Long =720000
    const val TOKEN_PREFIX: String = "Bearer "
    const val JWT_TOKEN_HEADER: String = "Jwt-Token"
    const val TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified"
    const val GET_ARRAYS_LLC = "Get Arrays, LLC"
    const val GET_ARRAYS_ADMINISTRATORS = "User Management Portal"
    const val AUTHORITIES = "Authorities"
    const val FORBIDDEN_MESSAGE = "You need to log in to access this resource"
    const val ACCESS_DENIED_MESSAGE = "You do not have permission to access this resource"
    const val OPTIONS_HTTP_METHOD = "OPTIONS"
    val PUBLIC_URLS = arrayOf("/user/login", "/user/register", "/user/image/**")


}