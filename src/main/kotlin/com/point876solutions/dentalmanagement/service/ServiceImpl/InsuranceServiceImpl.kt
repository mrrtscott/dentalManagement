package com.point876solutions.dentalmanagement.service.ServiceImpl

import com.point876solutions.dentalmanagement.models.Insurance
import com.point876solutions.dentalmanagement.repository.InsuranceRepository
import com.point876solutions.dentalmanagement.service.InsuranceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InsuranceServiceImpl: InsuranceService {

    @Autowired
    private var insuranceRepository: InsuranceRepository? = null

    override fun findInsuranceById(id: Long): Insurance? {
        return insuranceRepository?.findInsuranceById(id)
    }

}