package com.lms.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CheckoutRecord implements Serializable {

    private LocalDateTime checkoutDateTime;

    private LocalDateTime dueDateTime;

    private double fine;

    private LocalDate  finePaidDate;

    private BookCopy bookCopy;

    private LibraryMember libraryMember;

    public CheckoutRecord(LocalDateTime checkoutDateTime, LocalDateTime dueDateTime, BookCopy bookCopy,LibraryMember libraryMember){
        this.checkoutDateTime = checkoutDateTime;
        this.dueDateTime = dueDateTime;
        this.libraryMember = libraryMember;
        this.bookCopy = bookCopy;
    }

    public LocalDateTime getCheckoutDateTime() {
        return checkoutDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public double getFine() {
        return fine;
    }

    public LocalDate getFinePaidDate() {
        return finePaidDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }
}
