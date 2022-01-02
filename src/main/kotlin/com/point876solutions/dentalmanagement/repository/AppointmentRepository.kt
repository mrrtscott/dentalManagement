package com.point876solutions.dentalmanagement.repository

import com.point876solutions.dentalmanagement.models.Appointment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AppointmentRepository : JpaRepository<Appointment, Long>{

    fun findAppointmentsById(id: Long): Appointment?
}