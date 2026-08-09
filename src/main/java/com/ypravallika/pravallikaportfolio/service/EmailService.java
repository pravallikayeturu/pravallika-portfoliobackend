
package com.ypravallika.pravallikaportfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ypravallika.pravallikaportfolio.model.ContactMessage;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String resendApiKey;

    public void sendEmail(ContactMessage contact) {

        String url = "https://api.resend.com/emails";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(resendApiKey);

        String body = """
                {
                    "from": "onboarding@resend.dev",
                    "to": ["yeturupravallika94@gmail.com"],
                    "subject": "New Portfolio Contact: %s",
                    "html": "<h2>New Portfolio Contact</h2><p><strong>Name:</strong> %s</p><p><strong>Email:</strong> %s</p><p><strong>Subject:</strong> %s</p><p><strong>Message:</strong> %s</p>"
                }
                """.formatted(
                    contact.getSubject(),
                    contact.getName(),
                    contact.getEmail(),
                    contact.getSubject(),
                    contact.getMessage()
                );

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {

            restTemplate.postForEntity(
                    url,
                    request,
                    String.class
            );

            System.out.println("==================================");
            System.out.println("Email sent successfully using Resend!");
            System.out.println("==================================");

        } catch (Exception e) {

            System.out.println("==================================");
            System.out.println("RESEND EMAIL ERROR");
            System.out.println(e.getMessage());
            System.out.println("==================================");

            throw new RuntimeException(
                    "Failed to send email using Resend",
                    e
            );
        }
    }
}
