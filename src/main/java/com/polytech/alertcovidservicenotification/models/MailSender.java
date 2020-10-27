package com.polytech.alertcovidservicenotification.models;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class MailSender {
    private static MailSender instance = null;

    private MailSender(){}

    public static MailSender getInstance(){
        if(instance == null){
            instance = new MailSender();
        }
        return instance;
    }

    public void sendmail(String to) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("GMAIL_USER"), System.getenv("GMAIL_PASS"));
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(System.getenv("GMAIL_USER"), false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject("Alerte Cas Contact!");
        msg.setContent("<h2 style='color:red'>Vous êtes potentiellement cas contact!</h2>" +
                        "<p>Nous avons detecté que vous aviez potentiellement été en contact avec une personne qui a été dépisté postive au Covid-19!</p>" +
                        "Veuillez mettre en place les mesures de sécurité ci-joint afin de limiter les risques de propagation et de contamination",
                "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("<h2 style='color:red'>Vous êtes potentiellement cas contact!</h2>" +
                        "<p>Nous avons detecté que vous aviez potentiellement été en contact avec une personne qui a été dépisté postive au Covid-19!</p>" +
                        "Veuillez mettre en place les mesures de sécurité ci-jointes afin de limiter les risques de propagation et de contamination!",
                "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();
        var dir = System.getProperty("user.dir") + "/src/main/resources/files/fiche_personne_contact.pdf";
        attachPart.attachFile(dir);
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
