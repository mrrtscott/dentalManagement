package com.point876solutions.dentalmanagement.repository

import com.point876solutions.dentalmanagement.models.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository: JpaRepository<Invoice, Long> {

    fun findInvoiceById(id: Long): Invoice?
}