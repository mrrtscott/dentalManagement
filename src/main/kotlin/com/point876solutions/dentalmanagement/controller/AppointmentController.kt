package com.point876solutions.dentalmanagement.controller

import com.point876solutions.dentalmanagement.models.*
import com.point876solutions.dentalmanagement.service.ServiceImpl.AppointmentServiceImpl
import com.point876solutions.dentalmanagement.service.ServiceImpl.DentalChargeServiceImpl
import com.point876solutions.dentalmanagement.service.ServiceImpl.InsuranceServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime

@RestController
@RequestMapping("/appointment")
class AppointmentController {

    @Autowired
    private lateinit var appointmentService: AppointmentServiceImpl

    @Autowired
    lateinit var insuranceService: InsuranceServiceImpl

    @Autowired
    lateinit var dentalChargeService: DentalChargeServiceImpl

    @PostMapping("/add-invoice/{appointmentId}")
    fun createInvoice(@PathVariable appointmentId: Long, @RequestBody inputSale: Sale): ResponseEntity<Response> {
        val appointment: Appointment? = appointmentService.findAppointmentsById(appointmentId)

        val generatedInsuranceList = mutableListOf<Insurance>()

        if (inputSale.getInsurancePolicies() != null) {
            for (insurancePolicy in inputSale.getInsurancePolicies()!!){
                var tempInsurancePolicy =insuranceService.findInsuranceById(insurancePolicy)
                if (tempInsurancePolicy != null) {
                    generatedInsuranceList.add(tempInsurancePolicy)
                }
            }
        }


        val generatedDentalChargeList = mutableListOf<DentalCharge>()

        if(inputSale.getDentalCharges() != null){
            for (dentalCharge in inputSale.getDentalCharges()!!){
                var tempDentalCharge = dentalChargeService.findDentalChargeByCodes(dentalCharge)
                if (tempDentalCharge != null) {
                    generatedDentalChargeList.add(tempDentalCharge)
                }

            }
        }



        val invoice = Invoice(generatedInsuranceList,generatedDentalChargeList,inputSale.getInsurancePaymentValue())
        appointment?.addInvoice(invoice)
        if (appointment != null) {
            appointmentService.saveAppointment(appointment)
            //Inform user of new
        }
        val location = ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/{appointmentId}")
            .buildAndExpand(appointment?.getId()).toUri()
        val appointmentReceived: Appointment = appointmentService.findAppointmentsById(appointmentId)!!
        return ResponseEntity.created(location).body(Response(
            LocalDateTime.now(),
            HttpStatus.CREATED.value(),
            HttpStatus.CREATED,
            "",
            "",
            "",
            mapOf("appointment" to appointmentReceived)
        ))

    }

    @GetMapping("/{id}")
    fun getAppointment(@PathVariable id: Long): ResponseEntity<Response>{
        return ResponseEntity.ok().body(Response(
            LocalDateTime.now(),
            HttpStatus.OK.value(),
            HttpStatus.OK,
            "",
            "All appointments retrieved",
            "",
            mapOf("appointment" to (listOf(appointmentService.findAppointmentsById(id)))
            )))
    }
}