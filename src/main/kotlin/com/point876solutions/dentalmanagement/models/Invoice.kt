package com.point876solutions.dentalmanagement.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.point876solutions.dentalmanagement.models.Enum.InvoiceStatus
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @CreationTimestamp
    private var generatedDate: LocalDateTime? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "invoice_insurance",
        joinColumns = [javax.persistence.JoinColumn(name = "invoiceId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "insurance_id", referencedColumnName = "id")]
    )
    private var insurancePolicy: MutableList<Insurance>? = null



    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "invoice_dentalCharge",
        joinColumns = [javax.persistence.JoinColumn(name = "invoiceId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "dentalCharge_id", referencedColumnName = "codes")]
    )
    private var dentalCharge: MutableList<DentalCharge>? = null


    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "invoice_payment",
        joinColumns = [javax.persistence.JoinColumn(name = "invoiceId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "payment_id", referencedColumnName = "id")]
    )
    private var payment: MutableList<Payment>? = null


    private var subTotalDentalCharge: Double? = 0.00
    private var insurancePaymentValue: Double? = 0.00
    private var totalDue: Double? = 0.00
    private var taxCharge: Double? = 0.00
    private var amountPaid: Double? = 0.00
    private var balance: Double? = 0.00
    private var invoiceStatus: InvoiceStatus? = null

    constructor(
        insurancePolicy: MutableList<Insurance>?,
        dentalCharge: MutableList<DentalCharge>?,
        insurancePaymentValue: Double?,
    ) {
        this.insurancePolicy = insurancePolicy
        this.dentalCharge = dentalCharge
        this.insurancePaymentValue = insurancePaymentValue
        this.subTotalDentalCharge = calculateDentalCharges(this.dentalCharge)
        this.totalDue = (this.subTotalDentalCharge)?.minus((this.insurancePaymentValue!!))?.plus(this.taxCharge!!)
        this.taxCharge = subTotalDentalCharge?.times(0.015)
        this.balance = this.totalDue?.minus(this.amountPaid!!)
        calculatePayment()
        invoiceStatus = InvoiceStatus.CREATED
    }

    private fun calculateDentalCharges(dentalCharges: MutableList<DentalCharge>?): Double{
        var total = 0.00
        if (dentalCharges != null) {
            for (dentalCharge in dentalCharges){
                total += dentalCharge.getCharge()
            }
        }
        return total

    }

    private fun calculatePayment(){
        var total = 0.00
        if (this.payment != null) {
            for(pay in this.payment!!){
                total += pay.getValue()!!

            }
        }

        amountPaid = total

    }

    fun addPayment(payment: Payment){
        this.payment?.add(payment)
        calculatePayment()
        this.balance = this.totalDue?.minus(this.amountPaid!!)
        reEvaluateStatus()

    }

    private fun reEvaluateStatus(){
        if(this.balance!! <= 0){
            invoiceStatus = InvoiceStatus.PAID
        }
    }

    fun getId(): Long?{
        return this.id
    }

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    fun getGeneratedDate(): LocalDateTime? {
        return this.generatedDate
    }

    fun getInsurancePolicy(): MutableList<Insurance>? {
        return this.insurancePolicy
    }

    fun getDentalCharge(): MutableList<DentalCharge>? {
        return this.dentalCharge
    }

    fun getPayment(): MutableList<Payment>? {
        return this.payment
    }

    fun getSubTotalDentalCharge(): Double? {
        return this.subTotalDentalCharge
    }

    fun getInsurancePaymentValue(): Double? {
        return this.insurancePaymentValue
    }

    fun getTotalDue(): Double? {
        return this.totalDue
    }

    fun getTaxCharge(): Double? {
        return this.taxCharge
    }

    fun getAmountPaid(): Double? {
        return this.amountPaid
    }

    fun getBalance(): Double? {
        return this.balance
    }

    fun getInvoiceStatus(): InvoiceStatus? {
        return this.invoiceStatus
    }









}