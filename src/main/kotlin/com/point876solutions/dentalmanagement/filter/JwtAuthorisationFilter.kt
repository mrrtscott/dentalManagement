package com.point876solutions.dentalmanagement.filter

import com.point876solutions.dentalmanagement.constants.SecurityConstants
import com.point876solutions.dentalmanagement.utility.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthorisationFilter : OncePerRequestFilter() {

    @Autowired
    private var jwtTokenProvider: JwtTokenProvider? = null


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if(request.method.equals(SecurityConstants.OPTIONS_HTTP_METHOD, true)){
            response.status = HttpStatus.OK.value()

        } else {
            var authHeaders = request.getHeader(HttpHeaders.AUTHORIZATION)
            if (authHeaders == null || !authHeaders.startsWith(SecurityConstants.TOKEN_PREFIX,true)){
                filterChain.doFilter(request, response)
                return
                println("Not reach")
            }

            val token: String = authHeaders.substring(SecurityConstants.TOKEN_PREFIX.length)
            ("Reach")
            println(token)
            val username = jwtTokenProvider!!.getSubject(token)

            if (jwtTokenProvider!!.isValidToken(
                    username.toString(),
                    token
                ) && SecurityContextHolder.getContext().authentication == null
            ) {
                val authorities = jwtTokenProvider!!.getAuthorities(token)
                val authentication = jwtTokenProvider!!.getAuthentication(username, authorities, request)
                SecurityContextHolder.getContext().authentication = authentication
            } else {
                SecurityContextHolder.clearContext()
            }
        }

        filterChain.doFilter(request, response)

    }
}