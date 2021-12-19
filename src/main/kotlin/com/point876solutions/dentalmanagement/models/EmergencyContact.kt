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


}