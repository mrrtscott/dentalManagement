package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.PhoneType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Phone(
    private var areaCode: String?,
    private var telephoneNumber: String?,
    private var receiveCall: Boolean?,
    private var receiveText: Boolean?,
    private var type: PhoneType?
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = 0

    fun getAreaCode(): String?{
        return this.areaCode
    }

    fun getTelephoneNumber(): String?{
        return this.telephoneNumber
    }

    fun getReceiveCall(): Boolean?{
        return this.receiveCall
    }

    fun getReceiveText(): Boolean?{
        return this.receiveText
    }

    fun getType(): PhoneType?{
        return this.type
    }

}