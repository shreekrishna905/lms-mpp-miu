package com.lms.service;

import com.lms.business.LibraryMember;

import java.util.HashMap;

public interface MemberService {

    void addMember(LibraryMember member);

    HashMap<String,LibraryMember> getAll();


}
