package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.*
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
    private var oralHygieneCondition: OralHygieneCondition? = null
    private var calculusCondition:  CalculusCondition? = null
    private var plaqueCondition: PlaqueCondition? = null
    private var gingivalBleedingCondition: GingivalBleedingCondition? = null
    private var perioExam: PerioExam? = null
    private var comments: String? = null


}