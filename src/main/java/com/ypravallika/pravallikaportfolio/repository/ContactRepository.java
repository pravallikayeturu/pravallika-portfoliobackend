package com.ypravallika.pravallikaportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ypravallika.pravallikaportfolio.model.ContactMessage;

public interface ContactRepository
        extends JpaRepository<ContactMessage, Integer> {

}