package com.ypravallika.pravallikaportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ypravallika.pravallikaportfolio.model.ContactMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendEmail(ContactMessage contact) {

        // Check what Spring is reading
        System.out.println("==================================");
        System.out.println("FROM EMAIL : " + fromEmail);
        System.out.println("==================================");

        SimpleMailMessage message = new SimpleMailMessage();

        // Sender
        message.setFrom(fromEmail);

        // Receiver (your email)
        message.setTo("yeturupravallika94@gmail.com");

        // Subject
        message.setSubject("New Portfolio Contact: " + contact.getSubject());

        // Email Body
        message.setText(
                "Name: " + contact.getName() + "\n\n" +
                "Email: " + contact.getEmail() + "\n\n" +
                "Subject: " + contact.getSubject() + "\n\n" +
                "Message:\n" + contact.getMessage());

        // Send email
        mailSender.send(message);

        System.out.println("Email sent successfully!");
    }
}