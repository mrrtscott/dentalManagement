package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.InsuranceStatus
import java.util.*
import javax.persistence.*

@Entity
class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    private var provider: String? = null
    private var policyNumber: String? = null
    private var policyHolderFirstName: String? = null
    private var policyHolderMiddleName: String? = null
    private var policyHolderLastName: String? = null
    private var relationshipWithPolicyHolder: String? = null
    private var expiryDate: Date? = null
    @Enumerated(EnumType.STRING)
    private var status: InsuranceStatus? = null


    constructor(
        provider: String?,
        policyNumber: String?,
        policyHolderFirstName: String?,
        policyHolderMiddleName: String?,
        policyHolderLastName: String?,
        relationshipWithPolicyHolder: String?,
        expiryDate: Date?
    ) {
        this.provider = provider
        this.policyNumber = policyNumber
        this.policyHolderFirstName = policyHolderFirstName
        this.policyHolderMiddleName = policyHolderMiddleName
        this.policyHolderLastName = policyHolderLastName
        this.relationshipWithPolicyHolder = relationshipWithPolicyHolder
        this.expiryDate = expiryDate
        this.status = InsuranceStatus.ACTIVE
    }

    fun getId(): Long?{
        return this.id
    }

    fun getProvider(): String?{
        return this.provider
    }

    fun getPolicyNumber(): String?{
        return this.policyNumber
    }

    fun getPolicyHolderFirstName(){

    }

    fun getPolicyHolderMiddleName(){

    }

    fun getPolicyHolderLastName(){

    }

    fun getRelationshipWithPolicyHolder(): String?{
        return this.relationshipWithPolicyHolder
    }

    fun getExpiryDate(): Date? {
        return this.expiryDate
    }


    fun getStatus(): InsuranceStatus?{
        return this.status
    }


}