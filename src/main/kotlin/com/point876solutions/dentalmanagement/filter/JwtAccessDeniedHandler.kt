package com.point876solutions.dentalmanagement.filter

import org.springframework.security.web.access.AccessDeniedHandler
import kotlin.Throws
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import java.util.Locale
import com.fasterxml.jackson.databind.ObjectMapper
import com.point876solutions.dentalmanagement.constants.SecurityConstants
import com.point876solutions.dentalmanagement.models.HttpResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component
import java.io.OutputStream


@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {
    @Throws(IOException::class)
    override fun handle(request: HttpServletRequest, response: HttpServletResponse, exception: AccessDeniedException) {
        val httpResponse: HttpResponse = HttpResponse(
            HttpStatus.UNAUTHORIZED.value(),
            HttpStatus.UNAUTHORIZED,
            HttpStatus.UNAUTHORIZED.reasonPhrase.uppercase(Locale.getDefault()),
            SecurityConstants.ACCESS_DENIED_MESSAGE
        )
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.UNAUTHORIZED.value()
        val outputStream: OutputStream = response.outputStream
        val mapper = ObjectMapper()
        mapper.writeValue(outputStream, httpResponse)
        outputStream.flush()
    }
}