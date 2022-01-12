package com.point876solutions.dentalmanagement.models

import java.io.IOException
import java.util.*
import javax.mail.*
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class Mailer {


    private val PORT = "2525"
    private val HOST = "smtp.mailtrap.io"
    private val USERNAME = "321e7328982cf1"
    private val PASSWORD = "70aef1fb599dfb"
    private val EMAIL = "e4fc473cdb-8bf6a4@inbox.mailtrap.io"
    private val AUTH = true
    private val STARTTLS = true



    constructor()
    @Throws(MessagingException::class, IOException::class, AddressException::class)
    fun sendMail(patient: Patient)   {

        var message = MimeMessage(setProperties()?.let { setSession(it) });
        message?.sentDate = Date()
        message?.subject = "New Invoice Created"
        message?.setFrom(InternetAddress(EMAIL, false))
        message?.setRecipients(Message.RecipientType.TO, InternetAddress.parse(patient.getEmail()));
        message?.setContent(patient.getFirstName() + (", a new invoice has been created for you."), "text/html")


        Transport.send(message);


    }

    private fun setSession(props: Properties): Session? {
        return Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(USERNAME, PASSWORD)
            }
        })
    }


    private fun setProperties(): Properties? {
        val props = Properties()
        props["mail.smtp.port"] = PORT
        props["mail.smtp.host"] = HOST
        props["mail.smtp.auth"] = AUTH
        props["mail.smtp.starttls.enable"] = STARTTLS
        return props
    }


}