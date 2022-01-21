package com.lms.service;

import com.lms.business.LibraryMember;

import java.util.HashMap;
import java.util.List;

public interface MemberService {

    void addMember(LibraryMember member);

    LibraryMember findById(String memberId);

    List<LibraryMember> search(String keyWord);

    HashMap<String,LibraryMember> getAll();


}
