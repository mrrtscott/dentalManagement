package com.point876solutions.dentalmanagement.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Arrays.stream
import java.util.stream.Collectors

class UserPrincipal : UserDetails {

    private var user: User? = null

    constructor(user: User){
        this.user = user
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.user?.getAuthorities()?.map { authority -> SimpleGrantedAuthority(authority) }!!
            .stream().collect(Collectors.toList())
    }



    override fun getPassword(): String {
        return this.user?.getPassword().toString()
    }

    override fun getUsername(): String {
        return this.user?.getUsername().toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return this.user?.getIsNotLocked()!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return this.user?.getIsActive()!!
    }




}