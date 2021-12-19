package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.PhoneType
import javax.persistence.*

@Entity
class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    private var areaCode: String? = null
    private var telephoneNumber: String? = null
    private var receiveCall: Boolean? = null
    private var receiveText: Boolean? = null
    @Enumerated(EnumType.STRING)
    private var type: PhoneType? = null

    constructor(
        areaCode: String?,
        telephoneNumber: String?,
        receiveCall: Boolean?,
        receiveText: Boolean?,
        type: PhoneType?
    ) {
        this.areaCode = areaCode
        this.telephoneNumber = telephoneNumber
        this.receiveCall = receiveCall
        this.receiveText = receiveText
        this.type = type
    }

    fun getId(): Long?{
        return this.id
    }


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

    fun setAreaCode(areaCode: String?){
        this.areaCode = areaCode
    }

    fun setTelephoneNumber(telephoneNumber: String?){
        this.telephoneNumber = telephoneNumber
    }

    fun setReceiveCall(receiveCall: Boolean?){
        this.receiveCall = receiveCall
    }

    fun setReceiveText(receiveText: Boolean?){
        this.receiveText = receiveText
    }

    fun setType(type: PhoneType?){
        this.type = type
    }



}