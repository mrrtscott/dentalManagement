package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.PaymentType
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    @CreationTimestamp
    private var paymentDate: Date? = null
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
    constructor(paymentDate: Date?, type: PaymentType?, cardType: String?, lastFourDigits: String?, value: Double?) {
        this.paymentDate = paymentDate
        this.type = type
        this.cardType = cardType
        this.lastFourDigits = lastFourDigits
        this.value = value
    }

    //Cheque
    constructor(
        paymentDate: Date?,
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
    constructor(paymentDate: Date?, type: PaymentType?, value: Double?) {
        this.paymentDate = paymentDate
        this.type = type
        this.value = value
    }




    fun getValue(): Double?{
        return this.value
    }







}