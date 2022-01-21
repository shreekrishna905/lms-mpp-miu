package com.lms.service;

import com.lms.business.LibraryMember;
import com.lms.dataaccess.DataAccess;

import java.util.HashMap;

public class MemberServiceImpl implements MemberService {

    private DataAccess dataAccess;

    public MemberServiceImpl(DataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    @Override
    public void addMember(LibraryMember member) {
         dataAccess.saveNewMember(member);
    }

    @Override
    public HashMap<String, LibraryMember> getAll() {
        return dataAccess.readMemberMap();
    }
}
