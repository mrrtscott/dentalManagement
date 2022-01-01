package com.point876solutions.dentalmanagement.service

import com.point876solutions.dentalmanagement.models.Phone
import org.springframework.data.jpa.repository.JpaRepository

interface PhoneService  {

    fun savePhone(phone: Phone)
}