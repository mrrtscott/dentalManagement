package com.point876solutions.dentalmanagement.service.ServiceImpl

import com.point876solutions.dentalmanagement.models.Appointment
import com.point876solutions.dentalmanagement.repository.AppointmentRepository
import com.point876solutions.dentalmanagement.service.AppointmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppointmentServiceImpl: AppointmentService {

    @Autowired
    private var appointmentRepository: AppointmentRepository? = null

    override fun saveAppointment(appointment: Appointment): Appointment? {
        return appointmentRepository?.save(appointment)
    }

    override fun findAppointmentsById(id: Long): Appointment? {
        return appointmentRepository?.findAppointmentsById(id)
    }


}