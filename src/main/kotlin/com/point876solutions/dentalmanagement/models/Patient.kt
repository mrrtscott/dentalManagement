package com.point876solutions.dentalmanagement.models

import com.point876solutions.dentalmanagement.models.Enum.Sex
import java.util.*
import javax.persistence.*

@Entity
class Patient {

    @Id
    private var id: Long =0
    private var firstName: String? = null
    private var middleName: String? = null
    private var lastName: String? = null
    private var dateOfBirth: Date? = null
    @Enumerated(EnumType.STRING)
    private var sex: Sex? = null
    private var trn: String? = null
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "patient_addresses",
        joinColumns = [javax.persistence.JoinColumn(name = "patientId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "address_id", referencedColumnName = "id")]
    )
    private var address: List<Address>? = null


}