package com.point876solutions.dentalmanagement.service

import com.point876solutions.dentalmanagement.constants.EmailConstant.*
import com.sun.mail.smtp.SMTPTransport
import org.springframework.stereotype.Service
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
class EmailService {
    @Throws(MessagingException::class)
    fun sendNewPasswordEmail(firstName: String, password: String, email: String) {
        val message = createEmail(firstName, password, email)
        val smtpTransport: SMTPTransport = emailSession.getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL) as SMTPTransport
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD)
        smtpTransport.sendMessage(message, message.allRecipients)
        smtpTransport.close()
    }

    @Throws(MessagingException::class)
    private fun createEmail(firstName: String, password: String, email: String): Message {
        val message: Message = MimeMessage(emailSession)
        message.setFrom(InternetAddress(FROM_EMAIL))
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false))
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(CC_EMAIL, false))
        message.subject = EMAIL_SUBJECT
        message.setText("Hello $firstName, \n \n Your new account password is: $password\n \n The Support Team")
        message.sentDate = Date()
        message.saveChanges()
        return message
    }

    private val emailSession: Session
        private get() {
            val properties = System.getProperties()
            properties[SMTP_HOST] = GMAIL_SMTP_SERVER
            properties[SMTP_AUTH] = true
            properties[SMTP_PORT] = DEFAULT_PORT
            properties[SMTP_STARTTLS_ENABLE] = true
            properties[SMTP_STARTTLS_REQUIRED] = true
            return Session.getInstance(properties, null)
        }
}