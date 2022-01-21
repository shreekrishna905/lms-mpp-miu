package com.lms.controller;

import com.lms.business.Author;
import com.lms.business.Book;
import com.lms.business.Duration;
import com.lms.dataaccess.DataAccessFacade;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class BookViewController implements Initializable {

    private DataAccessFacade dataAccessFacade = new DataAccessFacade();

    private ObservableList<Book> bookObservableList = FXCollections.observableArrayList(dataAccessFacade.readBooksMap().values());

    @FXML
    private TableColumn isbn;
//
//    @FXML
//    private TableColumn author;
//
//    @FXML
//    private TableColumn duration;

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
            boolean isBookAvailable = searchBookInDatabase(isbn);
            if (isBookAvailable) {
//                System.out.println("found");
                Book book = dataAccessFacade.readBooksMap().get(isbn);
//                System.out.println(book.getTitle());
                inflateBookDataToView(book);
//                setPositiveLabel();
//                showHideView(true);
            } else {
//                setNegativeLabel();
//                showHideView(false);
            }
        } else {
//            showEmptyLabel();
        }
    }

    private boolean searchBookInDatabase(String isbn) {
        boolean isBookAvailable = false;
        Book book = dataAccessFacade.readBooksMap().get(isbn);
        if (book != null) {
            isBookAvailable = true;
        }
        return isBookAvailable;
    }

    private void inflateBookDataToView(Book book) {
//        isbn.setText(book.getIsbn());
//        bookTitle.setText(book.getTitle());
//        author.setText(getAuthorsFromBook(book.getAuthors()));
//        numberOfCopies.setText(String.valueOf(book.getNumCopies()));
    }

    public void loadBooksInTable() {
        bookObservableList.forEach(book -> {

            isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));

//            author.setCellValueFactory(new PropertyValueFactory<>(s.toString()));
//            duration.setCellValueFactory(new PropertyValueFactory<>(book.getDuration().getValue()));
        });
        tableBook.setItems(bookObservableList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBooksInTable();
    }


}
