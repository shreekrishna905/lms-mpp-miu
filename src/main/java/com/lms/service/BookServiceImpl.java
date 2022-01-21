package com.lms.service;

import com.lms.business.Book;
import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.DataAccessFacade;

public class BookServiceImpl implements BookService{

    private DataAccess dataAccessFacade;

    public BookServiceImpl(DataAccessFacade dataAccessFacade){
        dataAccessFacade = new DataAccessFacade();
    }

    @Override
    public void save(Book book) {
        dataAccessFacade.saveNewBook(book);
    }
}
