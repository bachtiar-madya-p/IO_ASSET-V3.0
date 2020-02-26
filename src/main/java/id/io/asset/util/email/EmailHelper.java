/**
  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
  *
  * Copyright (c) 2019 IO-Teknologi Indonesia, and individual contributors
  * as indicated by the @author tags. All Rights Reserved
  *
  * The contents of this file are subject to the terms of the
  * Common Development and Distribution License (the License).
  *
  * Everyone is permitted to copy and distribute verbatim copies
  * of this license document, but changing it is not allowed.
  *
  */
package id.io.asset.util.email;

import id.io.asset.util.log.AppLogger;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 

public class EmailHelper { 

    private static final AppLogger log = new AppLogger(EmailHelper.class);

    public static void sendMail(String host, String port, String sender,
            String recipient, String subject, String body) {

        log.info("sendMail", "Start");

        try {
            Session session = Session.getInstance(getProperties(host, port));

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(sender);
            msg.setRecipients(Message.RecipientType.TO, recipient);
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            Transport.send(msg);

        } catch (MessagingException e) {
            log.error("sendMail", e);
        }
        log.info("sendMail", "Completed");
    }

    public static void sendMail(String host, String port, boolean secured,
            String sender, String recipient, String subject, String body) {

        log.info("sendMail", "Start");

        try {
            Session session = Session.getInstance(getProperties(host, port, secured));

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(sender);
            msg.setRecipients(Message.RecipientType.TO, recipient);
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            Transport.send(msg);

        } catch (MessagingException e) {
            log.error("sendMail", e);
        }
        log.info("sendMail", "Completed");
    }

    public static void sendMail(String host, String port, String sender,
            String recipient, String ccAddresses,
            String subject, String body) {

        log.info("sendMail", "Start");

        try {
            Session session = Session.getInstance(getProperties(host, port));

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(sender);
            msg.setRecipients(Message.RecipientType.TO, recipient);
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddresses));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            Transport.send(msg);

        } catch (MessagingException e) {
            log.error("sendMail", e);
        }
        log.info("sendMail", "Completed");
    }

    public static void sendMail(String host, String port,
            boolean secured, String sender,
            String recipient, String ccAddresses,
            String subject, String body) {

        log.info("sendMail", "Start");

        try {
            Session session = Session.getInstance(getProperties(host, port, secured));

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(sender);
            msg.setRecipients(Message.RecipientType.TO, recipient);
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddresses));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            Transport.send(msg);

        } catch (MessagingException e) {
            log.error("sendMail", e);
        }
        log.info("sendMail", "Completed");
    }

    public static void sendMail(String host, String port,
            String sender, String recipient,
            String subject, String body,
            String username, String password) {

        log.info("sendMail", "Start - With Authentication");

        try {

            Authenticator authenticator = authenticate(username, password);
            Session session = Session.getInstance(getProperties(host, port), authenticator);

            Message msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(sender));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);

        } catch (MessagingException e) {
            log.error("sendMail", e);
        }
        log.info("sendMail", "Completed - With Authentication");
    }

    public static void sendMail(String host, String port,
            String sender, String recipient,
            String ccAddresses, String subject, String body,
            String username, String password) {

        log.info("sendMail", "Start - With Authentication");

        try {

            Authenticator authenticator = authenticate(username, password);
            Session session = Session.getInstance(getProperties(host, port), authenticator);

            Message msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(sender));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddresses));

            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);

        } catch (MessagingException e) {
            log.error("sendMail", e);
        }
        log.info("sendMail", "Completed - With Authentication");
    }

    public static void sendMail(String host, String port,
            boolean secure, String sender, String recipient,
            String subject, String body,
            String username, String password, boolean authRequired) {

        log.info("sendMail", "Start - With Authentication and Secured");

        try {

            Authenticator authenticator = authenticate(username, password);
            Session session = Session.getInstance(getProperties(host, port, secure, authRequired), authenticator);

            Message msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(sender));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);

        } catch (MessagingException e) {
            log.error("sendMail", e);
        }
        log.info("sendMail", "Completed - With Authentication and Secured");
    }

    public static void sendMail(String host, String port,
            boolean secure, String sender, String recipient,
            String ccAddresses, String subject, String body,
            String username, String password, boolean authRequired) {

        log.info("sendMail", "Start - With Authentication and Secured");

        try {

            Authenticator authenticator = authenticate(username, password);
            Session session = Session.getInstance(getProperties(host, port, secure, authRequired), authenticator);

            Message msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(sender));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddresses));

            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);

        } catch (MessagingException e) {
            log.error("sendMail", e);
        }
        log.info("sendMail", "Completed - With Authentication and Secured");
    }

    private static Properties getProperties(String host, String port) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        return properties;
    }

    private static Properties getProperties(String host, String port, boolean secured) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", secured);
        return properties;
    }

    private static Properties getProperties(String host, String port, boolean isSecure, boolean authRequired) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", authRequired);
        properties.put("mail.smtp.starttls.enable", isSecure);
        return properties;
    }

    private static Authenticator authenticate(String username, String password) {
        log.info("authentication", "Start");
        Authenticator authenticator
                = new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        log.info("authentication", "Completed");
        return authenticator;
    }

}
