package com.point876solutions.dentalmanagement.service

import com.point876solutions.dentalmanagement.models.Appointment

interface AppointmentService {
    fun saveAppointment(appointment: Appointment): Appointment?
    fun findAppointmentsById(id: Long): Appointment?
}