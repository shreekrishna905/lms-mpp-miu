package com.lms.controller;

import com.lms.business.Author;
import com.lms.business.Book;
import com.lms.business.Duration;
import com.lms.business.LibraryMember;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.exception.InvalidMemberException;
import com.lms.service.BookService;
import com.lms.service.BookServiceImpl;
import com.lms.service.MemberService;
import com.lms.service.MemberServiceImpl;
import com.lms.utils.Constants;
import com.lms.utils.LmsDialog;
import com.lms.utils.Validator;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BookViewController implements Initializable {

    private DataAccessFacade dataAccessFacade = new DataAccessFacade();

    private BookService bookService = new BookServiceImpl(new DataAccessFacade());

    private ObservableList<Book> bookObservableList = FXCollections.observableArrayList(dataAccessFacade.readBooksMap().values());

    @FXML
    private TableColumn isbn;

    @FXML
    private TableColumn authorFirstNames;

    @FXML
    private TableColumn duration;

    @FXML
    private TextField isbnSearchText;

    @FXML
    private Button searchBook;

    @FXML
    private TableView tableBook;


    @FXML
    private TableColumn title;

    @FXML
    public void searchBook(ActionEvent event) {
        System.out.println("check");
        dataAccessFacade = new DataAccessFacade();
        String isbn = isbnSearchText.getText().trim();
        if (!isbn.isBlank()) {
                Book book = dataAccessFacade.readBooksMap().get(isbn);

                String keyWord = isbnSearchText.getText();
                try {
                    Validator.validateSearchKeyWord(keyWord);
                    ObservableList<Book> list =FXCollections.observableArrayList(bookService.search(keyWord.toLowerCase()));
                    tableBook.setItems(list);
                }catch(InvalidMemberException e){
                    LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE,e.getMessage());
                    return;
                }
        }
    }


    public void loadBooksInTable() {
        bookObservableList.forEach(book -> {
//            System.out.println(book);
//            StringBuilder s = new StringBuilder();
//            for(Author author : book.getAuthors()) {
//                s.append(author.getFirstName() + " ");
//            }

            isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorFirstNames.setCellValueFactory(new PropertyValueFactory<>("authors"));
            duration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        });
        tableBook.setItems(bookObservableList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBooksInTable();
    }


}
