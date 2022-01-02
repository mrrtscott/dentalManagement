package com.point876solutions.dentalmanagement.service.ServiceImpl

import com.point876solutions.dentalmanagement.models.DentalCharge
import com.point876solutions.dentalmanagement.repository.DentalChargeRepository
import com.point876solutions.dentalmanagement.service.DentalChargeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DentalChargeServiceImpl: DentalChargeService {
    @Autowired
    private var dentalChargeRepository: DentalChargeRepository? = null

    override fun findDentalChargeByCodes(codes: String): DentalCharge? {
        return dentalChargeRepository?.findDentalChargeByCodes(codes)
    }
}