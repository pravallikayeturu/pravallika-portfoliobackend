```java
package com.ypravallika.pravallikaportfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.resend.Resend;
import com.resend.ResendException;
import com.resend.CreateEmailOptions;
import com.resend.CreateEmailResponse;

import com.ypravallika.pravallikaportfolio.model.ContactMessage;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String resendApiKey;

    public void sendEmail(ContactMessage contact) {

        try {

            Resend resend = new Resend(resendApiKey);

            CreateEmailOptions params = CreateEmailOptions.builder()
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

            CreateEmailResponse response = resend.emails().send(params);

            System.out.println("==================================");
            System.out.println("Email sent successfully using Resend!");
            System.out.println("Email ID: " + response.getId());
            System.out.println("==================================");

        } catch (ResendException e) {

            System.out.println("==================================");
            System.out.println("RESEND EMAIL ERROR");
            System.out.println(e.getMessage());
            System.out.println("==================================");

            throw new RuntimeException(
                    "Failed to send email using Resend", e
            );
        }
    }
}
```
