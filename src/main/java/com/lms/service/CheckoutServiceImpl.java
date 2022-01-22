package com.lms.service;

import com.lms.business.Book;
import com.lms.business.BookCopy;
import com.lms.business.CheckoutRecord;
import com.lms.business.LibraryMember;
import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
        Book book = bookMaps.get(isbn);
        BookCopy copy = book.getAvailableBook();
        LibraryMember libraryMember = memberMaps.get(memberId);
        int dueDate = Integer.parseInt(copy.getBook().getDuration().getValue());
        copy.setAvailable(false);
        CheckoutRecord checkoutRecord = new CheckoutRecord(LocalDateTime.now(),LocalDateTime.now().plusDays(dueDate),copy,libraryMember);
        List<BookCopy> copies = book.getBookCopies();
        copies.remove(copy);
        book.getBookCopies().add(copy);
        libraryMember.addCheckoutRecord(checkoutRecord);
        dataAccess.saveNewCheckout(checkoutRecord);
        dataAccess.saveNewBook(book);
        dataAccess.saveNewMember(libraryMember);
        return checkoutRecord;
    }

    @Override
    public CheckoutRecord searchByIsbn(String isbn) {
        HashMap<String, CheckoutRecord> mems = new DataAccessFacade().readCheckoutMap();

        CheckoutRecord record = mems.values().stream().filter(r-> {
            System.out.println("Checked out ISBN NO: "+r.getBookCopy().getBook().getIsbn());
            return (r.getBookCopy().getBook().getIsbn().equals(isbn.toLowerCase()));
        }).findFirst().orElse(null);

        return record;
    }
}
