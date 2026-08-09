
package com.ypravallika.pravallikaportfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.ypravallika.pravallikaportfolio.model.ContactMessage;

@Service
public class EmailService {

    @Value("${RESEND_API_KEY}")
    private String resendApiKey;

    public void sendEmail(ContactMessage contact) {

        try {
            Resend resend = new Resend(resendApiKey);

            SendEmailRequest request = SendEmailRequest.builder()
                    .from("onboarding@resend.dev")
                    .to("yeturupravallika94@gmail.com")
                    .subject("New Portfolio Contact: " + contact.getSubject())
                    .html(
                            "<h2>New Portfolio Contact</h2>" +
                            "<p><strong>Name:</strong> " + contact.getName() + "</p>" +
                            "<p><strong>Email:</strong> " + contact.getEmail() + "</p>" +
                            "<p><strong>Subject:</strong> " + contact.getSubject() + "</p>" +
                            "<p><strong>Message:</strong></p>" +
                            "<p>" + contact.getMessage() + "</p>"
                    )
                    .build();

            resend.emails().send(request);

            System.out.println("==================================");
            System.out.println("Email sent successfully using Resend!");
            System.out.println("==================================");

        } catch (ResendException e) {

            System.out.println("==================================");
            System.out.println("RESEND EMAIL ERROR");
            System.out.println(e.getMessage());
            System.out.println("==================================");

            throw new RuntimeException("Failed to send email using Resend", e);
        }
    }
}
