package com.point876solutions.dentalmanagement.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email

@Entity
class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    private var firstName: String? = null
    private var middleName: String? = null
    private var lastName: String ? = null
    @Email
    private var email: String? = null
    private var phoneNumber: String? = null
    private var receiveCall: Boolean? = null
    private var receiveEmail: Boolean? = null
    private var receiveText: Boolean? = null
    private var notes: String? = null
    private var expiryDate: Date? = null

}