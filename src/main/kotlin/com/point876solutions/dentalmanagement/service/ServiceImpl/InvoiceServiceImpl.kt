package com.point876solutions.dentalmanagement.service.ServiceImpl

import com.point876solutions.dentalmanagement.models.Invoice
import com.point876solutions.dentalmanagement.repository.InvoiceRepository
import com.point876solutions.dentalmanagement.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceServiceImpl : InvoiceService {

    @Autowired
    private var invoiceRepository: InvoiceRepository? = null

    override fun findInvoiceId(id: Long): Invoice? {
        return invoiceRepository?.findInvoiceById(id)
    }

    override fun saveInvoice(invoice: Invoice): Invoice? {
        return invoiceRepository?.save(invoice)
    }


}