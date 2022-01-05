package com.point876solutions.dentalmanagement.models

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
class MedicalRecordEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id:Long? = null
    @CreationTimestamp
    private var recordDate: Date? = null
    @Column(columnDefinition = "LONGTEXT")
    private var physicalFinding: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var diagnosis: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var prognosis: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var restriction: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var treatment: String?= null
    @Column(columnDefinition = "LONGTEXT")
    private var prescription: String? = null

    constructor(
        physicalFinding: String?,
        diagnosis: String?,
        prognosis: String?,
        restriction: String?,
        treatment: String?,
        prescription: String?
    ) {
        this.recordDate = recordDate
        this.physicalFinding = physicalFinding
        this.diagnosis = diagnosis
        this.prognosis = prognosis
        this.restriction = restriction
        this.treatment = treatment
        this.prescription = prescription
    }


    @JsonFormat(pattern="yyyy-MM-dd")
    fun getRecordDate(): Date? {
        return this.recordDate
    }

    fun getPhysicalFinding(): String? {
        return this.physicalFinding
    }

    fun getDiagnosis(): String? {
        return this.diagnosis
    }

    fun getPrognosis(): String? {
        return this.prognosis
    }

    fun getRestriction(): String? {
        return this.restriction
    }

    fun getTreatment(): String? {
        return this.treatment
    }


    fun getPrescription(): String? {
        return this.prescription
    }


}