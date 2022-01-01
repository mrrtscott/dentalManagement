package com.point876solutions.dentalmanagement.controller

import com.point876solutions.dentalmanagement.models.Patient
import com.point876solutions.dentalmanagement.models.Phone
import com.point876solutions.dentalmanagement.models.Response
import com.point876solutions.dentalmanagement.service.ServiceImpl.PatientServiceImpl
import com.point876solutions.dentalmanagement.service.ServiceImpl.PhoneServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/phone")
class PhoneController {
    @Autowired
    private lateinit var phoneServiceImpl: PhoneServiceImpl

    @Autowired
    private lateinit var patientService: PatientServiceImpl






}