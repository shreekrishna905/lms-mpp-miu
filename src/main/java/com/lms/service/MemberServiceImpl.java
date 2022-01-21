package com.lms.service;

import com.lms.business.LibraryMember;
import com.lms.dataaccess.DataAccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public LibraryMember findById(String memberId) {
        Map<String, LibraryMember> records = dataAccess.readMemberMap();
        return records.get(memberId);
    }

    @Override
    public List<LibraryMember> search(String keyWord) {
        return getAll().values().stream().filter(mem -> {
            return ( mem.getFirstName().toLowerCase().contains(keyWord)
                    || mem.getLastName().toLowerCase().contains(keyWord)
                    || mem.getPhoneNumber().toLowerCase().contains(keyWord)
                    || mem.getAddress().toString().toLowerCase().contains(keyWord)
                    || mem.getMemberId().contains(keyWord)
            );
        }).collect(Collectors.toList());
    }

    @Override
    public HashMap<String, LibraryMember> getAll() {
        return dataAccess.readMemberMap();
    }
}
