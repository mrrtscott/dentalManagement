package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.AppointmentStatus
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class Appointment(
    private var requestedAppointmentDate: LocalDateTime,
    @Enumerated(EnumType.STRING)
    private var appointmentStatus: AppointmentStatus,
    private var notes: String?
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    private lateinit var setAppointmentDate: LocalDateTime

}