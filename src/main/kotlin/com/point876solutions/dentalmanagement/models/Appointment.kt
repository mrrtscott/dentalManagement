package com.point876solutions.dentalmanagement.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.point876solutions.dentalmanagement.models.Enum.AppointmentStatus
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    private var requestedAppointmentDate: Date? = null
    private var appointmentReminder: Boolean? = null

    @Column(columnDefinition = "LONGTEXT")
    private var notes: String? = null

    private  var setAppointmentDate: Date? = null

    @Enumerated(EnumType.STRING)
    private var appointmentStatus: AppointmentStatus = AppointmentStatus.CREATED



    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "appointment_invoice",
        joinColumns = [javax.persistence.JoinColumn(name = "appointmentId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "invoice_id", referencedColumnName = "id")]
    )
    private var invoice: MutableList<Invoice>? = null




    constructor(requestedAppointmentDate: Date?, appointmentReminder: Boolean?, notes: String?) {
        this.requestedAppointmentDate = requestedAppointmentDate
        this.appointmentReminder = appointmentReminder
        this.notes = notes
        this.appointmentStatus = AppointmentStatus.CREATED

    }


    fun getId(): Long?{
        return this.id
    }

    fun getAppointmentReminder(): Boolean?{
        return this.appointmentReminder
    }

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    fun getRequestedAppointmentDate(): Date?{
        return this.requestedAppointmentDate
    }

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    fun getSetAppointmentDate(): Date? {
        return this.setAppointmentDate
    }

    fun getAppointmentStatus(): AppointmentStatus{
        return this.appointmentStatus
    }

    fun getNotes(): String?{
        return this.notes
    }

    fun getInvoice(): MutableList<Invoice>? {
        return this.invoice
    }




    fun setRequestedAppointmentDate(requestedAppointmentDate: Date){
        this.requestedAppointmentDate = requestedAppointmentDate
    }

    fun addInvoice(invoice: Invoice){
        this.invoice?.add(invoice)
    }

}