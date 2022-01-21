package com.lms.business;

import java.io.Serializable;
import java.util.Objects;

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

    public Book getBook() {
        return book;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy bookCopy = (BookCopy) o;
        return isAvailable == bookCopy.isAvailable && Objects.equals(bookCopyNumber, bookCopy.bookCopyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookCopyNumber, isAvailable);
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
