
package com.ypravallika.pravallikaportfolio.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ypravallika.pravallikaportfolio.model.ContactMessage;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String resendApiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendEmail(ContactMessage contact) {

        String url = "https://api.resend.com/emails";

        try {

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(resendApiKey);

            Map<String, Object> emailData = new HashMap<>();

            emailData.put("from", "onboarding@resend.dev");
            emailData.put("to", new String[]{"yeturupravallika94@gmail.com"});
            emailData.put(
                    "subject",
                    "New Portfolio Contact: " + contact.getSubject()
            );

            String html = """
                    <h2>New Portfolio Contact</h2>
                    <p><strong>Name:</strong> %s</p>
                    <p><strong>Email:</strong> %s</p>
                    <p><strong>Subject:</strong> %s</p>
                    <p><strong>Message:</strong> %s</p>
                    """.formatted(
                            escapeHtml(contact.getName()),
                            escapeHtml(contact.getEmail()),
                            escapeHtml(contact.getSubject()),
                            escapeHtml(contact.getMessage())
                    );

            emailData.put("html", html);

            String body = objectMapper.writeValueAsString(emailData);

            HttpEntity<String> request =
                    new HttpEntity<>(body, headers);

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
    System.out.println("Exception: " + e.getClass().getName());
    System.out.println("Message: " + e.getMessage());
    e.printStackTrace();
    System.out.println("==================================");

    throw new RuntimeException(
            "Failed to send email using Resend",
            e
    );
}
    }

    private String escapeHtml(String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
