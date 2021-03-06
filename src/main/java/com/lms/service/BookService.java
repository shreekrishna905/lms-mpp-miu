package com.lms.service;

import com.lms.business.Book;
import java.util.HashMap;
import java.util.List;

public interface BookService {
    List<Book> search(String keyWord);

    Book searchByIsbn(String isbn);

    HashMap<String, Book> getAll();

    void save(Book book);
}
