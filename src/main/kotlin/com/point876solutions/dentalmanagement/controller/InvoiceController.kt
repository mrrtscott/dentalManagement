package com.point876solutions.dentalmanagement.controller


import com.point876solutions.dentalmanagement.models.Invoice
import com.point876solutions.dentalmanagement.models.Payment
import com.point876solutions.dentalmanagement.models.Response
import com.point876solutions.dentalmanagement.service.ServiceImpl.InvoiceServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/invoice")
class InvoiceController {

    @Autowired
    private lateinit var invoiceService: InvoiceServiceImpl

    @PostMapping("/add-payment/{invoiceId}")
    fun createPayment(@PathVariable invoiceId: Long, @RequestBody inputPayment: Payment): ResponseEntity<Response> {

        var invoice = invoiceService.findInvoiceId(invoiceId)
        var payment:Payment? = null
        if(inputPayment.toString().uppercase(Locale.getDefault()) == "CASH"){
            payment = Payment(inputPayment.getType(), inputPayment.getValue())
        }

        if(inputPayment.getType().toString().uppercase(Locale.getDefault()) == "CHEQUE"){
            payment = Payment(inputPayment.getType(), inputPayment.getDrawer(), inputPayment.getPayee(), inputPayment.getChequeNumber(), inputPayment.getChequeDate(), inputPayment.getRoutingNumber(), inputPayment.getAccountNumber(), inputPayment.getValue())
        }

        if(inputPayment.getType().toString().uppercase(Locale.getDefault()) in listOf("CREDIT", "DEBIT")){
            payment = Payment(inputPayment.getType(), inputPayment.getCardType(), inputPayment.getLastFourDigits(), inputPayment.getValue())
        }


        if (payment != null) {
            invoice?.addPayment(payment)
        }

        if (invoice != null) {
            invoiceService.saveInvoice(invoice)
        }

        val location = ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/{patientId}")
            .buildAndExpand(invoice?.getId()).toUri()
        val receivedInvoice: Invoice = invoiceService.findInvoiceId(invoiceId)!!

        return ResponseEntity.created(location).body(
            Response(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                "",
                "",
                "",
                mapOf("Invoice" to receivedInvoice)
            ))

    }

}