package com.lms.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book implements Serializable {
    private String isbn;
    private Duration duration;
    private String title;
    private List<BookCopy> bookCopies;
    private List<Author> authors;

    public Book(String isbn ,String title, Duration duration, List<Author> authors) {
        this.isbn = isbn;
        this.duration = duration;
        this.title = title;
        this.bookCopies = new ArrayList<>();
        this.authors = Collections.unmodifiableList(authors);
    }

    public String getIsbn() {
        return isbn;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addCopy(){
        String copyNumber = String.valueOf(Math.random());
        BookCopy bookCopy = new BookCopy(copyNumber, true, this);
        this.bookCopies.add(bookCopy);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", duration=" + duration +
                ", title='" + title + '\'' +
                ", bookCopies=" + bookCopies +
                ", authors=" + authors +
                '}';
    }
}
