package com.point876solutions.dentalmanagement.models

class Sale {

    private var insurancePolicy =  mutableListOf<Long>()
    private var dentalCharge = mutableListOf<String>()
    private var insurancePaymentValue: Double? = 0.00

    constructor(
        insurancePolicy: MutableList<Long>,
        dentalCharge: MutableList<String>,
        insurancePaymentValue: Double?
    ) {
        this.insurancePolicy = insurancePolicy
        this.dentalCharge = dentalCharge
        this.insurancePaymentValue = insurancePaymentValue
    }


    fun getInsurancePolicies(): MutableList<Long>?{
        return this.insurancePolicy
    }

    fun getDentalCharges(): MutableList<String>?{
        return this.dentalCharge
    }

    fun getInsurancePaymentValue(): Double?{
        return this.insurancePaymentValue
    }
}