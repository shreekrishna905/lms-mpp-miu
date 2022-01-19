package com.lms.dataaccess;

import java.util.HashMap;

import com.lms.business.Book;
import com.lms.business.LibraryMember;
import com.lms.dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
}
