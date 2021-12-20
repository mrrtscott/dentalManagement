package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.ActiveStatus
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
class EmergencyContact(
    private var firstName: String?,
    private var middleName: String?,
    private var lastName: String?,
    private var relationshipToPatient: String?,
    @Email
    private var email: String?,
    private var phoneNumber: String?,
    private var receiveCall: Boolean?,
    private var receiveEmail: Boolean?,
    private var receiveText: Boolean?,
    private var notes: String?,
    private var expiryDate: Date?,
    @Enumerated(EnumType.STRING)
    private var status: ActiveStatus?
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    fun getFirstName(): String?{
        return this.firstName
    }

    fun getMiddleName(): String? {
        return this.middleName
    }

    fun getLastName(): String?{
        return this.lastName
    }

    fun getRelationshipToPatient(): String?{
        return this.relationshipToPatient
    }

    fun getEmail(): String?{
        return this.email
    }

    fun getPhoneNumber(): String?{
        return this.phoneNumber
    }

    fun getReceiveCall(): Boolean?{
        return this.receiveCall
    }

    fun getReceiveEmail(): Boolean?{
        return this.receiveEmail
    }

    fun getReceiveText(): Boolean?{
        return this.receiveText
    }

    fun getNotes(): String?{
        return this.notes
    }

    fun getExpiryDate(): Date?{
        return this.expiryDate
    }

    fun getStatus(): ActiveStatus?{
        return this.status
    }


}