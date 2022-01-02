package com.point876solutions.dentalmanagement.service

import com.point876solutions.dentalmanagement.models.Insurance

interface InsuranceService {

    fun findInsuranceById(id: Long): Insurance?
}