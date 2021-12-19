package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.Sex
import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Patient {

    @Id
    private var id: Long =0
    private var firstName: String? = null
    private var middleName: String? = null
    private var lastName: String? = null
    private var dateOfBirth: Date? = null
    @Enumerated(EnumType.STRING)
    private var sex: Sex? = null
    private var trn: String? = null
    private var address: Address? = null


}