package com.point876solutions.dentalmanagement.models

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.util.*

class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "America/Jamaica")
    var timeStamp: Date? = null
    var httpStatusCode // 200, 201, 400, 500
            = 0
    var httpStatus: HttpStatus? = null
    var reason: String? = null
    var message: String? = null

    // Constructor never used. Can be (and should be) deleted
    constructor() {}
    constructor(httpStatusCode: Int, httpStatus: HttpStatus?, reason: String?, message: String?) {
        timeStamp = Date()
        this.httpStatusCode = httpStatusCode
        this.httpStatus = httpStatus
        this.reason = reason
        this.message = message
    }
}