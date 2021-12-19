package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.MartialStatus
import com.point876solutions.dentalmanagement.models.Enum.PatientStatus
import com.point876solutions.dentalmanagement.models.Enum.Sex
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long =0
    @Email(message = "Email format not valid")
    private var email: String?= null
    private var firstName: String? = null
    private var middleName: String? = null
    private var lastName: String? = null
    private var dateOfBirth: Date? = null
    @Enumerated(EnumType.STRING)
    private var sex: Sex? = null
    private var trn: String? = null

    @Enumerated(EnumType.STRING)
    private var maritalStatus: MartialStatus? = null
    @Enumerated(EnumType.STRING)
    private var patientStatus: PatientStatus = PatientStatus.ALIVE

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_emergency_contacts",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "emergency_contact_id", referencedColumnName = "id")]
    )
    private var emergencyContact: List<EmergencyContact>? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_addresses",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "address_id", referencedColumnName = "id")]
    )
    private var address: List<Address>? = null
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_phone",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "phone_id", referencedColumnName = "id")]
    )
    private var phone: List<Phone>? = null


}