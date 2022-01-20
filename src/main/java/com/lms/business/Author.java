package com.lms.business;

import java.io.Serializable;

public class Author implements Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private boolean hasCredential;
    private String shortBio;

    public Author(String firstName, String lastName, String phoneNumber, Address address, String shortBio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.shortBio = shortBio;
    }
}
