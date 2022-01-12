package com.point876solutions.dentalmanagement.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.point876solutions.dentalmanagement.constants.SecurityConstants
import com.point876solutions.dentalmanagement.models.HttpResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import java.io.OutputStream
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : Http403ForbiddenEntryPoint() {
    @Throws(IOException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        val httpResponse = HttpResponse(
            HttpStatus.FORBIDDEN.value(),
            HttpStatus.FORBIDDEN,
            HttpStatus.FORBIDDEN.getReasonPhrase().uppercase(Locale.getDefault()),
            SecurityConstants.FORBIDDEN_MESSAGE
        )
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.FORBIDDEN.value()
        val outputStream: OutputStream = response.getOutputStream()
        val mapper = ObjectMapper()
        mapper.writeValue(outputStream, httpResponse)
        outputStream.flush()
    }
}