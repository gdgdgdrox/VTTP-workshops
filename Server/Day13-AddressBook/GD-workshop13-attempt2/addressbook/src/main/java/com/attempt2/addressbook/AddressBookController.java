package com.attempt2.addressbook;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    Contacts contacts;

    @GetMapping("/showform")
    public String showForm(Model model){
        model.addAttribute("addressBook", new AddressBook());
        return "showForm";
    }

    @PostMapping("/contact")
    public String showListOfPerson(@ModelAttribute AddressBook addressBook, Model model){
        contacts.saveInformation(addressBook);
        model.addAttribute("listOfAddress", AddressBook.listOfAddress);
        return "listOfPerson";
    }


}
