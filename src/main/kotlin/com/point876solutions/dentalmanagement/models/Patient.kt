package com.point876solutions.dentalmanagement.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.point876solutions.dentalmanagement.models.Enum.MartialStatus
import com.point876solutions.dentalmanagement.models.Enum.PatientStatus
import com.point876solutions.dentalmanagement.models.Enum.Sex
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
class Patient{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @Email(message = "Email format not valid")
    @Column(unique = true, updatable = true)
    private var email: String? = null
    private var firstName: String? = null
    private var middleName: String? = null
    private var lastName: String? = null
    private var dateOfBirth: Date? = null
    @Enumerated(EnumType.STRING)
    private var sex: Sex? = null
    @Column(unique = true, updatable = false)
    private var trn: String? = null
    @Enumerated(EnumType.STRING)
    private var maritalStatus: MartialStatus? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_emergency_contacts",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "emergency_contact_id", referencedColumnName = "id")]
    )
    private var emergencyContact: MutableList<EmergencyContact>? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_addresses",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "address_id", referencedColumnName = "id")]
    )
    private var address: MutableList<Address>? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_phone",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "phone_id", referencedColumnName = "id")]
    )
    private var phone: MutableList<Phone>? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_appointment",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "appointment_id", referencedColumnName = "id")]
    )
    private var appointment: MutableList<Appointment>? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_insurance",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "insurance_id", referencedColumnName = "id")]
    )
    private var insurance: MutableList<Insurance>? = null




    @Enumerated(EnumType.STRING)
    private var patientStatus: PatientStatus = PatientStatus.ALIVE

    constructor(
        email: String?,
        firstName: String?,
        middleName: String?,
        lastName: String?,
        dateOfBirth: Date?,
        sex: Sex?,
        trn: String?,
        maritalStatus: MartialStatus?,
        emergencyContact: MutableList<EmergencyContact>?,
        address: MutableList<Address>?,
        phone: MutableList<Phone>?,
        appointment: MutableList<Appointment>?
    ) {
        this.email = email
        this.firstName = firstName
        this.middleName = middleName
        this.lastName = lastName
        this.dateOfBirth = dateOfBirth
        this.sex = sex
        this.trn = trn
        this.maritalStatus = maritalStatus
        this.emergencyContact = emergencyContact
        this.address = address
        this.phone = phone
        this.appointment = appointment
        this.patientStatus = PatientStatus.ALIVE
    }


    fun getId(): Long?{
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


    fun getEmergencyContact(): MutableList<EmergencyContact>?{
        return this.emergencyContact
    }

    fun getAddress(): MutableList<Address>?{
        return this.address
    }

    fun getPhone(): List<Phone>?{
        return this.phone
    }

    fun getAppointment(): List<Appointment>?{
        return this.appointment
    }

    fun getInsurance(): List<Insurance>?{
        return this.insurance
    }

    fun setPhone(phone: MutableList<Phone>){
        this.phone = phone
    }

    fun addEmergencyContact(emergencyContact: EmergencyContact){
        this.emergencyContact?.add(emergencyContact)
    }

    fun addAddress(address: Address){
        this.address?.add(address)
    }

    fun addPhone(phone: Phone){
        this.phone?.add(phone)
    }

    fun addAppointment(appointment: Appointment){
        this.appointment?.add(appointment)
    }

    fun addInsurance(insurance: Insurance){
        this.insurance?.add(insurance)

    }







}