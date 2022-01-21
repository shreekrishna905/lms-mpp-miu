package com.lms.business;

import java.io.Serializable;

public class BookCopy implements Serializable {
    private String bookCopyNumber;
    private boolean isAvailable;

    private  Book book;

    public BookCopy(String bookCopyNumber, boolean isAvailable, Book book) {
        this.bookCopyNumber = bookCopyNumber;
        this.isAvailable = isAvailable;
        this.book = book;
    }

    public String getBookCopyNumber() {
        return bookCopyNumber;
    }

    public void setBookCopyNumber(String bookCopyNumber) {
        this.bookCopyNumber = bookCopyNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
