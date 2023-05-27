package com.attempt2.addressbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AddressBook implements Serializable {
    private String name;
    private String email;
    private String phoneNumber;
    private String id;
    public static List<AddressBook> listOfAddress = new ArrayList<>();
}
