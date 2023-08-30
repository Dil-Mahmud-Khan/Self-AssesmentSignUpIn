package com.project2morrow.lapp.Otp;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyMail{
    public static void send(String to,String otp){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("mahmud@project2morrow.com","lbtavbjdffmbywco");
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Verify your account");
            message.setContent(generateEmailTemplate(otp), "text/html");
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            System.out.println("message sent successfully");
            throw new RuntimeException(e);
        }

    }


    public static String generateEmailTemplate(String otp) {
        String template = "<html>" +
                "<head><style>" +
                "body { font-family: Arial, sans-serif; }" +
                ".container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
                ".header { background-color: #f5f5f5; padding: 20px; text-align: center; }" +
                ".content { padding: 20px; background-color: #ffffff; }" +
                "</style></head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<div class=\"header\">" +
                "<h1>One-Time Password</h1>" +
                "</div>" +
                "<div class=\"content\">" +
                "<p>Dear User,</p>" +
                "<p>Your One-Time Password (OTP) is: <strong>" + otp + "</strong></p>" +
                "<p>Please use this OTP to verify your account.</p>" +
                "<p>Thank you,</p>" +
                "<p>Project2Morrow Consulting Services</p>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        return template;
    }

}