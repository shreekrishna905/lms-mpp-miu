package com.lms.service;

import com.lms.business.Book;
import com.lms.business.BookCopy;
import com.lms.business.CheckoutRecord;
import com.lms.business.LibraryMember;
import com.lms.dataaccess.DataAccess;
import com.lms.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Map;

public class CheckoutServiceImpl implements CheckoutService{

    private DataAccess dataAccess;

    public CheckoutServiceImpl(DataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    @Override
    public CheckoutRecord checkout(String memberId, String isbn) {
        Map<String, LibraryMember> memberMaps =  dataAccess.readMemberMap();
        Map<String, Book> bookMaps = dataAccess.readBooksMap();
        if(!memberMaps.containsKey(memberId)){
            throw new EntityNotFoundException(String.format("Member with id:%s not found",memberId));
        }
        if(!bookMaps.containsKey(isbn)){
            throw new EntityNotFoundException(String.format("Book with isbn:%s not found",isbn));
        }
        BookCopy copy = bookMaps.get(isbn).getAvailableBook();
        LibraryMember libraryMember = memberMaps.get(memberId);
        int dueDate = Integer.parseInt(copy.getBook().getDuration().getValue());
        CheckoutRecord checkoutRecord = new CheckoutRecord(LocalDateTime.now(),LocalDateTime.now().plusDays(dueDate),copy,libraryMember);
        dataAccess.saveNewCheckout(checkoutRecord);
        return checkoutRecord;
    }
}
