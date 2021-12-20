package com.point876solutions.dentalmanagement.controller

import com.point876solutions.dentalmanagement.models.Patient
import com.point876solutions.dentalmanagement.models.Response
import com.point876solutions.dentalmanagement.service.ServiceImpl.PatientServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/patient")
class PatientController {

    @Autowired
    private lateinit var patientService: PatientServiceImpl

    @PostMapping
    fun createPatient(@RequestBody inputPatient: @Valid Patient): ResponseEntity<Response>{
        val patient = patientService.savePatient(inputPatient)
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(patient?.getId()).toUri()
        val patientId: Long = patient?.getId()?: -1
        val patientReceived:Patient = patientService.findPatientById(patientId)!!
        return ResponseEntity.created(location).body(Response(
            LocalDateTime.now(),
            HttpStatus.CREATED.value(),
            HttpStatus.CREATED,
            "",
            "",
            "",
            mapOf("Patient" to patientReceived)
        ))
    }

    @GetMapping
    fun getAllPatients():  ResponseEntity<Response>{
        return ResponseEntity.ok().body(Response(
            LocalDateTime.now(),
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "",
            "All vaccines retrieved",
            "",
            mapOf("patients" to (patientService.findAllPatients()?.toList() ?: listOf()))
        ))
    }
}