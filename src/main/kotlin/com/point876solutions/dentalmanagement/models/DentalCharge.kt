package com.point876solutions.dentalmanagement.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DentalCharge {

    @Id
    @Column(name = "codes")
    private var code: String? = null
    @Column(name = "procedures")
    private var procedure: String? = null
    private var charge: Double = 0.00


    fun getCharge(): Double{
        return this.charge
    }

    fun getCode(): String? {
        return this.code
    }

    fun getProcedure(): String? {
        return this.procedure
    }
}