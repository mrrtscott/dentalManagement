package com.point876solutions.dentalmanagement.repository

import com.point876solutions.dentalmanagement.models.Insurance
import org.springframework.data.jpa.repository.JpaRepository

interface InsuranceRepository : JpaRepository<Insurance, Long> {
}