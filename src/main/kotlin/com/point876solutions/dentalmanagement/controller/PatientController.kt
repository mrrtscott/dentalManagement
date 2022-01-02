package com.point876solutions.dentalmanagement.controller

import com.point876solutions.dentalmanagement.models.*
import com.point876solutions.dentalmanagement.service.ServiceImpl.PatientServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime
import javax.validation.Valid

@Suppress("DuplicatedCode")
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
            "All patients retrieved",
            "",
            mapOf("patients" to (patientService.findAllPatients()?.toList() ?: listOf()))
        ))
    }

    @GetMapping("/{id}")
    fun getPatient(@PathVariable id: Long): ResponseEntity<Response>{
        return ResponseEntity.ok().body(Response(
            LocalDateTime.now(),
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "",
            "All patients retrieved",
            "",
            mapOf("patients" to (listOf(patientService.findPatientById(id)))
        )))
    }

    fun deletePatient(@PathVariable id: Long): ResponseEntity<Response>{
        return ResponseEntity.ok().body(Response(
            LocalDateTime.now(),
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "",
            "All patients retrieved",
            "",
            mapOf("patients" to (listOf(patientService.findPatientById(id)))
            )))
    }



    @PostMapping("/add-emergency-contact/{patientId}")
    fun createPhone (@PathVariable patientId: Long, @RequestBody inputEmergencyContact: @Valid EmergencyContact) : ResponseEntity<Response> {
        val patient = patientService.findPatientById(patientId)
        patient?.addEmergencyContact(inputEmergencyContact)
        if (patient != null) {
            patientService.savePatient(patient)
        }
        //need to fix location url
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{patientId}")
            .buildAndExpand(patient?.getId()).toUri()

        val patientReceived: Patient = patientService.findPatientById(patientId)!!
        return ResponseEntity.created(location).body(
            Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "",
                "",
                "",
                mapOf("Patient" to patientReceived)
            )
        )

    }


    @PostMapping("/add-phone/{patientId}")
    fun createPhone ( @PathVariable patientId: Long, @RequestBody inputPhone: @Valid Phone) : ResponseEntity<Response> {
        val patient = patientService.findPatientById(patientId)
        patient?.addPhone(inputPhone)
        if (patient != null) {
            patientService.savePatient(patient)
        }
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{patientId}")
            .buildAndExpand(patient?.getId()).toUri()

        val patientReceived:Patient = patientService.findPatientById(patientId)!!
        return ResponseEntity.created(location).body(Response(
            LocalDateTime.now(),
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "",
            "",
            "",
            mapOf("Patient" to patientReceived)
        ))

    }

    @PostMapping("/add-appointment/{patientId}")
    fun createAppointment(@PathVariable patientId: Long, @RequestBody inputAppointment: Appointment): ResponseEntity<Response> {
        println(inputAppointment.getAppointmentReminder())
        val patient = patientService.findPatientById(patientId)
        patient?.addAppointment(inputAppointment)
        if (patient != null) {
            patientService.savePatient(patient)
        }

        val location = ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/{patientId}")
            .buildAndExpand(patient?.getId()).toUri()
        val patientReceived:Patient = patientService.findPatientById(patientId)!!
        return ResponseEntity.created(location).body(
            Response(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                HttpStatus.OK,
                "",
                "",
                "",
                mapOf("Patient" to patientReceived)
            )
        )




    }

    @PostMapping("/add-insurance/{patientId}")
    fun createInsurance(@PathVariable patientId: Long, @RequestBody inputInsurance: @Valid Insurance) : ResponseEntity<Response>{

        val patient = patientService.findPatientById(patientId)
        patient?.addInsurance(inputInsurance)
        if (patient != null) {
            patientService.savePatient(patient)
        }

        val location = ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/{patientId}")
            .buildAndExpand(patient?.getId()).toUri()
        val patientReceived:Patient = patientService.findPatientById(patientId)!!
        return ResponseEntity.created(location).body(
            Response(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                "",
                "",
                "",
                mapOf("Patient" to patientReceived)
            )
        )


    }



}