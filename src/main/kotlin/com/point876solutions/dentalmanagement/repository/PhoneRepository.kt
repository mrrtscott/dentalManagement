package com.point876solutions.dentalmanagement.repository

import com.point876solutions.dentalmanagement.models.Phone
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PhoneRepository:JpaRepository<Phone, Long> {
}