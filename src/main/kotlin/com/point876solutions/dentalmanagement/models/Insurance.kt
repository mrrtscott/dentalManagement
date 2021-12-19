package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.InsuranceStatus
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = 0
    private var provider: String? = null
    private var policyNumber: String? = null
    private var policyHolderFirstName: String? = null
    private var policyHolderMiddleName: String? = null
    private var policyHolderLastName: String? = null
    private var relationshipWithPolicyHolder: String? = null
    private var expiryDate: Date? = null
    private var status: InsuranceStatus? = null
}