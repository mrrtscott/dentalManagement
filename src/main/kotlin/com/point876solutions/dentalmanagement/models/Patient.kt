package com.point876solutions.dentalmanagement.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.point876solutions.dentalmanagement.models.Enum.MartialStatus
import com.point876solutions.dentalmanagement.models.Enum.PatientStatus
import com.point876solutions.dentalmanagement.models.Enum.Sex
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
class Patient(
    @Email(message = "Email format not valid")
    @Column(unique = true, updatable = true)
    private var email: String?,
    private var firstName: String?,
    private var middleName: String?,
    private var lastName: String?,
    private var dateOfBirth: Date?,
    @Enumerated(EnumType.STRING)
    private var sex: Sex?,
    @Column(unique = true, updatable = false)
    private var trn: String?,
    @Enumerated(EnumType.STRING)
    private var maritalStatus: MartialStatus?,
    @Enumerated(EnumType.STRING)
    private var patientStatus: PatientStatus,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_emergency_contacts",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "emergency_contact_id", referencedColumnName = "id")]
    )
    private var emergencyContact: List<EmergencyContact>?,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_addresses",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "address_id", referencedColumnName = "id")]
    )
    private var address: List<Address>?,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_phone",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "phone_id", referencedColumnName = "id")]
    )
    private var phone: List<Phone>?,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_appointment",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "appointment_id", referencedColumnName = "id")]
    )
    private var appointment: List<Appointment>?
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long =0


    fun getId(): Long{
        return this.id
    }

    fun getEmail(): String?{
        return this.email
    }

    fun getFirstName():String?{
        return this.firstName
    }

    fun getMiddleName(): String?{
        return this.middleName
    }

    fun getLastName():String?{
        return this.lastName
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    fun getDateOfBirth(): Date?{
        return this.dateOfBirth
    }

    fun getSex(): Sex?{
        return this.sex
    }

    fun getTrn(): String?{
        return this.trn
    }

    fun getMaritalStatus():MartialStatus?{
        return this.maritalStatus
    }


    fun getPatientStatus(): PatientStatus{
        return this.patientStatus
    }


    fun getEmergencyContact(): List<EmergencyContact>?{
        return this.emergencyContact
    }

    fun getAddress(): List<Address>?{
        return this.address
    }

    fun getPhone(): List<Phone>?{
        return this.phone
    }

    fun getAppointment(): List<Appointment>?{
        return this.appointment
    }


}