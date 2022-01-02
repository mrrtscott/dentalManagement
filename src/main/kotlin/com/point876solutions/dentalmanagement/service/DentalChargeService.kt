package com.point876solutions.dentalmanagement.service

import com.point876solutions.dentalmanagement.models.DentalCharge

interface DentalChargeService {
    fun findDentalChargeByCodes(codes: String): DentalCharge?
}