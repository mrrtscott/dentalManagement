package com.point876solutions.dentalmanagement.service.ServiceImpl

import com.point876solutions.dentalmanagement.models.Phone
import com.point876solutions.dentalmanagement.repository.PhoneRepository
import com.point876solutions.dentalmanagement.service.PhoneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PhoneServiceImpl : PhoneService {

    @Autowired
    private var phoneRepository: PhoneRepository? = null

    override fun savePhone(phone: Phone) {
        phoneRepository?.save(phone)
    }
}