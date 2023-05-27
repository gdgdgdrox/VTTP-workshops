package com.attempt2.addressbook;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Contacts {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    Random random = new Random();

    public void saveInformation(AddressBook addressBook){
        int number = random.nextInt();
        String id = Integer.toHexString(number);
        addressBook.setId(id);
        redisTemplate.opsForValue().set(addressBook.getId(), addressBook);
        
    }
    
}
