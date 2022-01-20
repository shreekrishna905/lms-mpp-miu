package com.lms.business;

import java.io.Serializable;

public class LibraryMember implements Serializable {
    private String memberId;
    private  String firstName;
    private  String lastName;
    private  String phoneNumber;
    private  Address address;

    public LibraryMember(String memberId, String firstName, String lastName, String phoneNumber, Address address) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getMemberId() {
        return memberId;
    }
}
