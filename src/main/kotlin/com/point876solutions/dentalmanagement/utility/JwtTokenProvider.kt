package com.point876solutions.dentalmanagement.utility

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.JWTVerifier
import com.point876solutions.dentalmanagement.constants.SecurityConstants
import com.point876solutions.dentalmanagement.constants.SecurityConstants.GET_ARRAYS_LLC
import com.point876solutions.dentalmanagement.constants.SecurityConstants.TOKEN_CANNOT_BE_VERIFIED
import com.point876solutions.dentalmanagement.models.UserPrincipal
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider {

    @Value("\${jwt.secret}")
    private var secret: String? = null


     fun generateJwtToken(userPrincipal: UserPrincipal): String? {
        var claims :Array<String> = getClaims(userPrincipal)
        val localDate = LocalDate.now()
        val date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())


        return (JWT.create()
            .withIssuer(SecurityConstants.GET_ARRAYS_LLC)
            .withAudience(SecurityConstants.GET_ARRAYS_ADMINISTRATORS)
            .withIssuedAt(date)
            .withSubject(userPrincipal.username).withArrayClaim( SecurityConstants.AUTHORITIES, claims)
            .withExpiresAt(Date( System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(secret?.toByteArray())))


    }

    fun getAuthorities(token: String): List<GrantedAuthority>{
        var claims: Array<String> = getClaimsFromToken(token)
        return claims.toSet().stream().map { claim -> SimpleGrantedAuthority(
            claim.toString()
        ) }.collect(Collectors.toList())

    }


    fun getAuthentication(
        username: String?,
        authorities: List<GrantedAuthority?>?,
        request: HttpServletRequest?
    ): Authentication? {
        val userPasswordAuthToken = UsernamePasswordAuthenticationToken(username, null, authorities)
        userPasswordAuthToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        return userPasswordAuthToken
    }

    fun isValidToken(username: String?, token: String?): Boolean {
        val verifier: JWTVerifier = getVerifier()
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token!!)
    }

    fun getSubject(token: String?): String? {
        val verifier: JWTVerifier = getVerifier()
        return verifier.verify(token).subject
    }

    private fun isTokenExpired(verifier: JWTVerifier?, token: String): Boolean {
        var expiration: Date = verifier?.verify(token)!!.expiresAt
        return expiration.before(Date())


    }


    private fun getClaimsFromToken(token: String): Array<String> {
        val verifier: JWTVerifier = getVerifier()
        return verifier.verify(token).claims[SecurityConstants.AUTHORITIES]!!.asArray(String::class.java)

    }



    private  fun getVerifier(): JWTVerifier {
        val verifier: JWTVerifier = try {
            val algorithm = Algorithm.HMAC512(secret)
            JWT.require(algorithm).withIssuer(GET_ARRAYS_LLC).build()
        } catch (exception: JWTVerificationException) {
            throw JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED)
        }
        return verifier
    }



    private fun getClaims(userPrincipal: UserPrincipal): Array<String> {
        var authorities = mutableListOf<String>()
        for (grantedAuthority in userPrincipal.authorities){
            authorities.add(grantedAuthority.authority)
        }

        return authorities.toTypedArray()

    }
}