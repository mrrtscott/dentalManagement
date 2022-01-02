package com.point876solutions.dentalmanagement.repository

import com.point876solutions.dentalmanagement.models.Insurance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InsuranceRepository : JpaRepository<Insurance, Long> {

    fun findInsuranceById(id: Long): Insurance?
}