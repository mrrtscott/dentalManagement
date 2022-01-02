package com.point876solutions.dentalmanagement.repository

import com.point876solutions.dentalmanagement.models.DentalCharge
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DentalChargeRepository : JpaRepository<DentalCharge, String> {

    @Query("SELECT c FROM DentalCharge c WHERE c.code = :codes")
    fun findDentalChargeByCodes (codes: String): DentalCharge?
}