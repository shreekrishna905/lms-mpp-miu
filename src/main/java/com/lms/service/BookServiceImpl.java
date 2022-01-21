package com.lms.service;

import com.lms.business.Author;
import com.lms.business.Book;
import com.lms.dataaccess.DataAccess;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService{

    private DataAccess dataAccess;

    public BookServiceImpl(DataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    @Override
    public List<Book> search(String keyWord) {
        return getAll().values().stream().filter(book -> {
            Boolean containAuthor = mayContainsAuthor(book, keyWord);
            return ( book.getIsbn().toLowerCase().contains(keyWord)
                    || book.getTitle().toLowerCase().contains(keyWord)
                    || book.getDuration().toString().contains(keyWord)
                    || containAuthor
            );
        }).collect(Collectors.toList());
    }

    private boolean mayContainsAuthor(Book book, String keyword) {
        for (Author author:book.getAuthors()) {
            if(author.getFirstName().toLowerCase().contains(keyword))
                return true;
        }
        return false;
    }

    @Override
    public HashMap<String, Book> getAll() {
        return dataAccess.readBooksMap();
    }
}
