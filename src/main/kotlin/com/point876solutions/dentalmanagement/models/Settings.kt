package com.point876solutions.dentalmanagement.models

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Settings {

    @Id
    private var id: Long = 1

    private var businessName: String? = null
    private var tagline: String? = null
    private var trn: String? = null

    private var addressLine1: String? = null
    private var addressLine2: String? = null
    private var addressLine3: String? = null
    private var addressLine4: String? = null
    private var addressLine5: String? = null

    private var phone1: String? = null
    private var phone2: String? = null
    private var fax: String? = null


    private var website: String? = null
    private var email: String? = null




}