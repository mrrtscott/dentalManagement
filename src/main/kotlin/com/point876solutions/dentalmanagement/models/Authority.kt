package com.point876solutions.dentalmanagement.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    private var name: String? = null


    fun getId(): Long?{
        return this.id
    }


    fun getName(): String?{
        return this.name
    }
}