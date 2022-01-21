package com.lms.utils;

import com.lms.business.LibraryMember;
import com.lms.exception.InvalidMemberException;

public class Validator {

    public static void validateMember(LibraryMember member) throws InvalidMemberException {
        if(member.equals(null)) throw new InvalidMemberException("Member data couldnot be empty");

        if(member.getMemberId().isEmpty()) throw new InvalidMemberException("Member ID is empty");

        if(member.getFirstName().isEmpty()) throw new InvalidMemberException("First name is empty");

        if(member.getLastName().isEmpty()) throw new InvalidMemberException("Last name is empty");

        if(member.getPhoneNumber().isEmpty()) throw new InvalidMemberException("Phone number is empty");

        if(member.getAddress().equals(null)) throw new InvalidMemberException("Address is empty");

        if(member.getAddress().getCity().isEmpty()) throw new InvalidMemberException("City is empty");

        if(member.getAddress().getState().isEmpty()) throw new InvalidMemberException("State is empty");

        if(member.getAddress().getZip().isEmpty()) throw new InvalidMemberException("ZIP is empty");

        if(member.getAddress().getStreet().isEmpty()) throw new InvalidMemberException("Street is empty");
    }

    public static void validateSearchKeyWord(String keyWord) throws InvalidMemberException{

        if(keyWord.isEmpty()) throw new InvalidMemberException("Invalid Search keyword");
    }
}
