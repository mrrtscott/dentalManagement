package com.point876solutions.dentalmanagement.service.ServiceImpl

import com.point876solutions.dentalmanagement.models.Patient
import com.point876solutions.dentalmanagement.repository.PatientRepository
import com.point876solutions.dentalmanagement.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PatientServiceImpl :PatientService {

    @Autowired
    private var patientRepository:PatientRepository? = null

    override fun savePatient(patient: Patient):Patient? {
        return patientRepository?.save(patient)
    }

    override fun findPatientById(id: Long): Patient? {
        return patientRepository?.findPatientsById(id)
    }

    override fun findAllPatients(): List<Patient>? {
        return this.patientRepository?.findAll()
    }

    override fun findPatientsByFirstName(firstName: String): List<Patient>? {
        return patientRepository?.findPatientsByFirstName(firstName)
    }

    override fun findPatientsByLastName(lastName: String): List<Patient>? {
        return patientRepository?.findPatientsByLastName(lastName)
    }

    override fun findPatientsByEmail(email: String): List<Patient>? {
        return patientRepository?.findPatientsByEmail(email)
    }

    override fun deletePatient(id: Long) {
        patientRepository?.deleteById(id)
    }
}