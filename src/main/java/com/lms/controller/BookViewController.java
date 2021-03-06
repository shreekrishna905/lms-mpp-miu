package com.lms.controller;

import com.lms.business.Book;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.exception.InvalidMemberException;
import com.lms.service.BookService;
import com.lms.service.BookServiceImpl;
import com.lms.ui.*;
import com.lms.utils.ApplicationInfo;
import com.lms.utils.Constants;
import com.lms.utils.LmsDialog;
import com.lms.utils.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BookViewController extends MenuController implements Initializable {

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
    private TableColumn noOfAvailableCopies;


    @FXML
    private TextField isbnSearchText;

    @FXML
    private Button searchBook;

    @FXML
    private Button addCopy;

    @FXML
    private TableView tableBook;


    @FXML
    private TableColumn title;

    private Book selectedBook;

    @FXML
    public void searchBook(ActionEvent event) {
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

    @FXML
    public void addCopy(ActionEvent event) {
        selectedBook.addCopy();
        List<Book> books = bookObservableList.stream().filter(book -> !book.getIsbn().equals(selectedBook.getIsbn())).collect(Collectors.toList());
        books.add(selectedBook);
        bookObservableList.removeAll();
        tableBook.getItems().clear();
        bookObservableList.addAll(books);
        bookService.save(selectedBook);
    }

    public void loadBooksInTable() {
        bookObservableList.forEach(book -> {
            isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorFirstNames.setCellValueFactory(new PropertyValueFactory<>("authors"));
            duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            noOfAvailableCopies.setCellValueFactory(new PropertyValueFactory<>("noOfAvailableCopies"));
        });
        tableBook.setItems(bookObservableList);
    }

    @FXML
    public void logout(ActionEvent event){
        ApplicationInfo.currentAuth = null;
        ApplicationInfo.show(new LoginViewWindow());
    }

    @FXML
    public void addBook(){
        ApplicationInfo.show(new AddBookWindow());
    }

    @FXML
    public void showMember(ActionEvent event){
        ApplicationInfo.show(new MemberViewWindow());
    }

    @FXML
    public void showCheckout(ActionEvent event){
        ApplicationInfo.show(new CheckoutWindow());
    }

    @FXML
    public void showSearchCheckoutWindow(ActionEvent event){
        ApplicationInfo.show(new SearchCheckoutWindow());
    }

    @FXML
    public void showOverdue(ActionEvent event){
        ApplicationInfo.show(new OverdueWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBooksInTable();
        super.initialize(url,resourceBundle);
        tableBook.setRowFactory(yourTable -> {
            TableRow<Book> row = new TableRow<Book>();
            row.setOnMouseClicked(me -> {
                addCopy.setDisable(false);
                selectedBook = row.getItem();
            });

            return row;
        });
    }

}
