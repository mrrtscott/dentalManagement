package com.point876solutions.dentalmanagement.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.point876solutions.dentalmanagement.models.Enum.PaymentType
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    @CreationTimestamp
    private var paymentDate: Date? = null
    @Enumerated(EnumType.STRING)
    private var type: PaymentType? = null
    private var cardType: String? = null
    private var lastFourDigits: String? = null
    private var drawer: String? = null
    private var payee: String? = null
    private var chequeNumber: String? = null
    private var chequeDate: String? = null
    private var routingNumber: String? = null
    private var accountNumber: String? = null
    private var value: Double? = null

    //Debit and Credit Cards
    constructor(type: PaymentType?, cardType: String?, lastFourDigits: String?, value: Double?) {
        this.paymentDate = paymentDate
        this.type = type
        this.cardType = cardType
        this.lastFourDigits = lastFourDigits
        this.value = value
    }

    //Cheque
    constructor(
        type: PaymentType?,
        drawer: String?,
        payee: String?,
        chequeNumber: String?,
        chequeDate: String?,
        routingNumber: String?,
        accountNumber: String?,
        value: Double?
    ) {
        this.paymentDate = paymentDate
        this.type = type
        this.drawer = drawer
        this.payee = payee
        this.chequeNumber = chequeNumber
        this.chequeDate = chequeDate
        this.routingNumber = routingNumber
        this.accountNumber = accountNumber
        this.value = value
    }

    //Cash
    constructor(type: PaymentType?, value: Double?) {
        this.paymentDate = paymentDate
        this.type = type
        this.value = value
    }

    fun getId(): Long? {
        return this.id
    }

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    fun getPaymentDate(): Date? {
        return this.paymentDate
    }

     fun getType(): PaymentType? {
         return this.type
     }

    fun getCardType(): String? {
        return this.cardType
    }

    fun getLastFourDigits(): String? {
        return this.lastFourDigits
    }

    fun getDrawer(): String? {
        return this.drawer
    }

    fun getPayee(): String? {
        return this.payee

    }

    fun getChequeNumber(): String? {
        return this.chequeNumber

    }

    fun getChequeDate(): String? {
        return this.chequeDate

    }

    fun getRoutingNumber(): String? {
        return this.routingNumber

    }

    fun getAccountNumber(): String? {
        return this.accountNumber

    }


    fun getValue(): Double?{
        return this.value
    }


}