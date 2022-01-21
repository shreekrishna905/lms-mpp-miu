package com.lms.controller;

import com.lms.ApplicationStartUp;
import com.lms.business.Author;
import com.lms.business.Book;
import com.lms.business.Duration;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.service.BookService;
import com.lms.service.BookServiceImpl;
import com.lms.ui.BookViewWindow;
import com.lms.utils.ApplicationInfo;
import com.lms.utils.Constants;
import com.lms.utils.LmsDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddBookController implements Initializable {


    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtIsbn;

    @FXML
    private ComboBox cbDuration;

    @FXML
    private TextField txtNumberOfCopy;

    @FXML
    private TableView tblAuthor;

    private ObservableList<Author> authors = FXCollections.observableArrayList();

    private BookService bookService = new BookServiceImpl(new DataAccessFacade());

    @FXML
    private void addBook(ActionEvent event) throws IOException{
        if(isEmpty()){
            LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE,Constants.BOOK_EMPTY_INFO);
            return;
        }
        Book book = new Book(txtIsbn.getText().trim(),txtTitle.getText().trim(), Duration.valueOf(cbDuration.getValue().toString()),authors.stream().collect(Collectors.toList()));
        IntStream.rangeClosed(1,Integer.valueOf(txtNumberOfCopy.getText().trim())).forEach(
                i -> book.addCopy());
        bookService.save(book);
        ApplicationInfo.show(new BookViewWindow());
    }


    @FXML
    private void addAuthor(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("add-author.fxml"));
        Parent parent = fxmlLoader.load();
        AddAuthorController addAuthorController = fxmlLoader.getController();
        addAuthorController.setAuthors(authors);
        Scene scene = new Scene(parent, 742, 508);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private boolean isEmpty(){
        if(txtTitle.getText().trim().isEmpty() ||
            txtIsbn.getText().trim().isEmpty() ||
                txtNumberOfCopy.getText().trim().isEmpty() ||
                tblAuthor.getItems().isEmpty() ||
                cbDuration.getValue().toString().isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblAuthor.setItems(authors);
    }


}
