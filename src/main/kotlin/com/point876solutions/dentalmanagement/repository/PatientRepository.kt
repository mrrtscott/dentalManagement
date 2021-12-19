package com.point876solutions.dentalmanagement.repository

import com.point876solutions.dentalmanagement.models.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository:JpaRepository<Patient, Long> {

    fun findPatientsById(id:Long):Patient?
    fun findPatientsByFirstName(firstName:String):List<Patient>?
    fun findPatientsByLastName(lastName:String):List<Patient>
    fun findPatientsByEmail(email:String):List<Patient>
}