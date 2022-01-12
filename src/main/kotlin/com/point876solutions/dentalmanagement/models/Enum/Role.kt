package com.point876solutions.dentalmanagement.models.Enum

import com.point876solutions.dentalmanagement.constants.Authority.ADMIN_AUTHORITIES
import com.point876solutions.dentalmanagement.constants.Authority.HR_AUTHORITIES
import com.point876solutions.dentalmanagement.constants.Authority.MANAGER_AUTHORITIES
import com.point876solutions.dentalmanagement.constants.Authority.SUPER_ADMIN_AUTHORITIES
import com.point876solutions.dentalmanagement.constants.Authority.USER_AUTHORITIES

enum class Role(vararg authorities: String) {
    ROLE_USER(*USER_AUTHORITIES), ROLE_HR(*HR_AUTHORITIES), ROLE_MANAGER(*MANAGER_AUTHORITIES), ROLE_ADMIN(*ADMIN_AUTHORITIES), ROLE_SUPER_ADMIN(
        *SUPER_ADMIN_AUTHORITIES
    );

    val authorities: Array<String>

    init {
        this.authorities = authorities as Array<String>
    }
}