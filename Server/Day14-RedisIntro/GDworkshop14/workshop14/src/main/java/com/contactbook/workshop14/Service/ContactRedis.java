package com.contactbook.workshop14.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.contactbook.workshop14.Model.Contact;

@Service
public class ContactRedis implements ContactRepo {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(Contact contact) {
        redisTemplate.opsForValue().set(contact.getId(), contact);
        redisTemplate.opsForHash().put(Contact.redisKey, contact.getId(), contact);
    }

    @Override
    public Contact findById(String contactId) {
        Contact obj = (Contact) redisTemplate.opsForValue().get(contactId);
        return obj;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    // @Override
    // public List<Contact> findAllContacts() {
    //     List<Object> list = redisTemplate.opsForHash().values(Contact.redisKey);
    //     List<Contact> contactList = list.stream().map(o -> new Contact()).collect(Collectors.toList());
    //     for (Contact contact : contactList)
    //         System.out.println(contact);
    //     return contactList;
    // }

    
}
