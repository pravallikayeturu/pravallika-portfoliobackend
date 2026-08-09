package com.ypravallika.pravallikaportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ypravallika.pravallikaportfolio.model.ContactMessage;
import com.ypravallika.pravallikaportfolio.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repo;

    @Autowired
    private EmailService emailService;

    public void saveContact(ContactMessage contact) {

        repo.save(contact);

           emailService.sendEmail(contact);

    }
}