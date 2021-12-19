package com.point876solutions.dentalmanagement.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class InitialClinicalExamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = 0

    private var chiefDentalComplaint: String? = null
    private var oralHabits: String? = null
    private var existingIllness: String? = null
    private var drugsUsed: String? = null
    private var allergies: String? = null
    private var bloodPressure: String? = null
    private var pulse: String? = null
    private var dateOfLastPhysicalExam: String? = null
    private var generalPhysicalCondition: String? = null
    private var hadTuberculosis: Boolean? = null
    private var isUnderPhysicianCare: Boolean? = null
    private var hadBleeding: Boolean? = null
    private var reactionToAnesthetic: Boolean? = null



}