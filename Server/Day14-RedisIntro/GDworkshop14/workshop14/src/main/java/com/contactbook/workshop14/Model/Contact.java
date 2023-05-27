package com.contactbook.workshop14.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact implements Serializable{
   
    private String name;
    private String email;
    private int age;
    private String id;
    public static final String redisKey = "redisKey";

    
    public Contact(){
        this.id = idGenerator();
    }

    @Bean
    public Random generateRandom(){
        Random random = new Random();
        return random;
    }

    public String idGenerator(){
        int randomInt = generateRandom().nextInt();
        String id = Integer.toHexString(randomInt);
        return id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", age='" + getAge() + "'" +
            ", id='" + getId() + "'" +
            "}";
    }


}