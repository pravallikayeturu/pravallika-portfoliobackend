package com.ypravallika.pravallikaportfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ypravallika.pravallikaportfolio.model.ContactMessage;
import com.ypravallika.pravallikaportfolio.service.ContactService;

@CrossOrigin(origins = {
    "http://127.0.0.1:5501",
    "http://localhost:5501",
    "http://127.0.0.1:5500",
    "http://localhost:5500",
    "https://pravallika-portfolio-three.vercel.app",
        "https://pravallika-portfolio-git-main-pravallika9.vercel.app",
     "https://pravallika-portfolio-aor8hmzh8-pravallika9.vercel.app"
})
@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private ContactService service;

    @GetMapping("/test")
    public String home() {
        return "Backend Working Successfully";
    }

    @PostMapping("/contact")
    public String saveMessage(@RequestBody ContactMessage contact) {

        service.saveContact(contact);

        return "Message Send Successfully";
    }
}