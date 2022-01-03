package com.point876solutions.dentalmanagement.repository

import com.point876solutions.dentalmanagement.models.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository: JpaRepository<Payment, Long> {
}
