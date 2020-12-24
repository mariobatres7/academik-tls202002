package edu.academik.telus.javamail.example.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mario Batres
 */
@Stateless
public class MailService {

    @Resource(mappedName = "java:/jboss/mail/suertudo_viajero")
    private Session session;

    @Asynchronous
    public void sendMail() {

        try {

            String emailDestination = "edson_gabriel007@hotmail.com";
            
            System.out.println("Enviando a:  " + emailDestination);

            String content = "<h1>Correo de Prueba - Clase Telus </h1>";

            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html;charset=UTF-8");

            message.setFrom(new InternetAddress("mbatres@nabenik.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDestination));
            message.setSubject("Correo de Prueba::Clase Telus", "UTF-8");

            message.setContent(content, "text/html;charset=UTF-8");

            Transport.send(message);

        } catch (MessagingException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
