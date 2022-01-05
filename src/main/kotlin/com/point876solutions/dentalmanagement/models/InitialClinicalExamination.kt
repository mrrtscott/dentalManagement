package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
class InitialClinicalExamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = 0
    @CreationTimestamp
    private var date: Date? = null
    @Column(columnDefinition = "LONGTEXT")
    private var chiefDentalComplaint: String? = null
    private var oralHabits: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var existingIllness: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var drugsUsed: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var allergies: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var bloodPressure: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var pulse: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var dateOfLastPhysicalExam: String? = null
    @Column(columnDefinition = "LONGTEXT")
    private var generalPhysicalCondition: String? = null
    private var hadTuberculosis: Boolean? = null
    private var isUnderPhysicianCare: Boolean? = null
    private var hadBleeding: Boolean? = null
    private var reactionToAnesthetic: Boolean? = null
    @Enumerated(EnumType.STRING)
    private var oralHygieneCondition: OralHygieneCondition? = null
    @Enumerated(EnumType.STRING)
    private var calculusCondition:  CalculusCondition? = null
    @Enumerated(EnumType.STRING)
    private var plaqueCondition: PlaqueCondition? = null
    @Enumerated(EnumType.STRING)
    private var gingivalBleedingCondition: GingivalBleedingCondition? = null
    @Enumerated(EnumType.STRING)
    private var perioExam: PerioExam? = null
    private var comments: String? = null

    constructor(
        chiefDentalComplaint: String?,
        oralHabits: String?,
        existingIllness: String?,
        drugsUsed: String?,
        allergies: String?,
        bloodPressure: String?,
        pulse: String?,
        dateOfLastPhysicalExam: String?,
        generalPhysicalCondition: String?,
        hadTuberculosis: Boolean?,
        isUnderPhysicianCare: Boolean?,
        hadBleeding: Boolean?,
        reactionToAnesthetic: Boolean?,
        oralHygieneCondition: OralHygieneCondition?,
        calculusCondition: CalculusCondition?,
        plaqueCondition: PlaqueCondition?,
        gingivalBleedingCondition: GingivalBleedingCondition?,
        perioExam: PerioExam?,
        comments: String?
    ) {
        this.date = date
        this.chiefDentalComplaint = chiefDentalComplaint
        this.oralHabits = oralHabits
        this.existingIllness = existingIllness
        this.drugsUsed = drugsUsed
        this.allergies = allergies
        this.bloodPressure = bloodPressure
        this.pulse = pulse
        this.dateOfLastPhysicalExam = dateOfLastPhysicalExam
        this.generalPhysicalCondition = generalPhysicalCondition
        this.hadTuberculosis = hadTuberculosis
        this.isUnderPhysicianCare = isUnderPhysicianCare
        this.hadBleeding = hadBleeding
        this.reactionToAnesthetic = reactionToAnesthetic
        this.oralHygieneCondition = oralHygieneCondition
        this.calculusCondition = calculusCondition
        this.plaqueCondition = plaqueCondition
        this.gingivalBleedingCondition = gingivalBleedingCondition
        this.perioExam = perioExam
        this.comments = comments
    }

}