package com.contactbook.workshop14.Service;

import java.util.List;

import com.contactbook.workshop14.Model.Contact;

public interface ContactRepo {
    public void save(final Contact contact);

    public Contact findById(final String contactId);

    // public List<Contact> findAllContacts();

    public void delete (String id);
    // public void update (Contact contact)

    // public Contact 

    
    
}
