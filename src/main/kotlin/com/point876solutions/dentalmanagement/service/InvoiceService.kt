package com.point876solutions.dentalmanagement.service

import com.point876solutions.dentalmanagement.models.Invoice

interface InvoiceService {

    fun findInvoiceId(id: Long): Invoice?
    fun saveInvoice(invoice: Invoice): Invoice?
}