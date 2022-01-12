package com.point876solutions.dentalmanagement.models

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
class User : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private var id: Long? = null
    private var userID: String? = null
    private var firstName: String? = null
    private var middleName: String? = null
    private var lastName: String? = null
    private var email: String? = null
    private var username: String? = null
    private var password: String? = null
    private var profileImageUrl: String? = null
    private var lastLoginDate: Date? = null
    private var lastLoginDateDisplay: Date? = null
    private var joinDate: Date? = null
    private var role: String? = null

    @Column(columnDefinition = "TINYBLOB")
    private var authorities: Array<String>? = null

    private var isActive: Boolean = true
    private var isNotLocked: Boolean = true

    fun getId(): Long? {
        return this.id
    }

    fun getUserID(): String?{
        return  this.userID
    }

    fun getFirstName (): String?{
        return  this.firstName
    }

    fun getMiddleName (): String?{
        return this.middleName

    }
    fun getLastName (): String?{
        return  this.lastName

    }
    fun getEmail (): String?{
        return this.email

    }
    fun getUsername (): String?{
        return this.username

    }
    fun getPassword (): String?{
        return this.password

    }
    fun getProfileImageUrl (): String?{
        return this.profileImageUrl

    }
    fun getLastLoginDate (): Date?{
        return this.lastLoginDate

    }
    fun getLastLoginDateDisplay (): Date?{
        return this.lastLoginDateDisplay

    }
    fun getJoinDate (): Date?{
        return this.joinDate

    }

    fun getRoles (): String? {
        return this.role

    }

    fun getAuthorities (): Array<String>? {
        return  this.authorities

    }

    fun getIsActive (): Boolean{
        return this.isActive

    }

    fun getIsNotLocked (): Boolean{
        return  this.isNotLocked

    }


    fun setNotLocked(notLocked: Boolean) {
        isNotLocked = notLocked
    }

    fun setLastLoginDateDisplay(lastLoginDateDisplay: Date?) {
        this.lastLoginDateDisplay = lastLoginDateDisplay
    }

    fun setLastLoginDate(lastLoginDate: Date?) {
        this.lastLoginDate = lastLoginDate
    }

    fun setFirstName(firstName: String?) {
        this.firstName = firstName
    }

    fun setLastName(lastName: String?) {
        this.lastName = lastName
    }

    fun setUsername(username: String?) {
        this.username = username
    }


    fun setEmail(email: String?) {
        this.email = email
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun setUserId(userId: String) {
        this.userID = userId
    }

    fun setJoinDate(joinDate: Date?) {
        this.joinDate = joinDate
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun setActive(active: Boolean) {
        isActive = active
    }

    fun setRole(role: String) {
        this.role = role
    }

    fun setAuthorities(authorities: Array<String>?) {
        this.authorities = authorities
    }

    fun setProfileImageUrl(profileImageUrl: String?) {
        this.profileImageUrl = profileImageUrl
    }

    constructor()




    constructor(
        id: Long?,
        userID: String?,
        firstName: String?,
        middleName: String?,
        lastName: String?,
        email: String?,
        username: String?,
        password: String?,
        profileImageUrl: String?,
        lastLoginDate: Date?,
        lastLoginDateDisplay: Date?,
        joinDate: Date?,
        roles: String,
        authorities: Array<String>,
        isActive: Boolean,
        isNotLocked: Boolean
    ) {
        this.id = id
        this.userID = userID
        this.firstName = firstName
        this.middleName = middleName
        this.lastName = lastName
        this.email = email
        this.username = username
        this.password = password
        this.profileImageUrl = profileImageUrl
        this.lastLoginDate = lastLoginDate
        this.lastLoginDateDisplay = lastLoginDateDisplay
        this.joinDate = joinDate
        this.role = roles
        this.authorities = authorities
        this.isActive = isActive
        this.isNotLocked = isNotLocked
    }






}