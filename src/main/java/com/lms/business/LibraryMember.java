package com.lms.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LibraryMember implements Serializable {
    private String memberId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private List<CheckoutRecord> checkoutRecords;

    public LibraryMember(String memberId, String firstName, String lastName, String phoneNumber, Address address) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.checkoutRecords = new ArrayList<>();
    }

    public void addCheckoutRecord(CheckoutRecord checkoutRecord){
        this.checkoutRecords.add(checkoutRecord);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CheckoutRecord> getCheckoutRecords() {
        return checkoutRecords;
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "memberId='" + memberId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
}

