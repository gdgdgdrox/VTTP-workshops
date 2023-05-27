package com.contactbook.workshop14.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactbook.workshop14.Model.Contact;
import com.contactbook.workshop14.Service.ContactRedis;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactRedis service;

    @GetMapping
    public String homePage(Model model){
        model.addAttribute("contact", new Contact());
        return "homePage";
    }

    @PostMapping("/create")
    public String createContact(@ModelAttribute Contact contact){
        service.save(contact);
        return "confirmationPage";
    }

    @GetMapping("/read/{id}")
    public String retrieveContact(@PathVariable String id, Model model){
        model.addAttribute("contact", service.findById(id));
        return "contactInfo";
    }

    @GetMapping("/read/all")
    public String retrieveAllContacts(Model model){
        return "listOfContacts";
    }

    @PostMapping("/update/{id}")
    public String updateContact(){
        return null;
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(){
        return null;
    }





    
}
