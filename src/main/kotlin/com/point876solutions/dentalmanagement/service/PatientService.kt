package com.point876solutions.dentalmanagement.service

import com.point876solutions.dentalmanagement.models.Patient

interface PatientService {

    fun savePatient(patient: Patient): Patient?
    fun findPatientById(id: Long): Patient?
    fun findAllPatients(): List<Patient>?
    fun findPatientsByFirstName(firstName:String):List<Patient>?
    fun findPatientsByLastName(lastName:String):List<Patient>?
    fun findPatientsByEmail(email:String):List<Patient>?
    fun deletePatient(id: Long)
}