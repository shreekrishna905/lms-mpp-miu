package com.lms.controller;

import com.lms.business.Book;
import com.lms.business.CheckoutRecord;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.service.BookService;
import com.lms.service.BookServiceImpl;
import com.lms.service.CheckoutService;
import com.lms.service.CheckoutServiceImpl;
import com.lms.ui.CheckoutWindow;
import com.lms.ui.LoginViewWindow;
import com.lms.utils.ApplicationInfo;
import com.lms.utils.Constants;
import com.lms.utils.LmsDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class OverdueController {

    private CheckoutService checkoutService = new CheckoutServiceImpl(new DataAccessFacade());

    private BookService bookService = new BookServiceImpl(new DataAccessFacade());

    private RowData rowData;

    @FXML
    TextField isbnSearchTxtField;

    @FXML
    TableView overdueTable;


    @FXML
    public void checkout(){
        ApplicationInfo.show(new CheckoutWindow());
    }

    @FXML
    public void logout(ActionEvent event){
        ApplicationInfo.currentAuth = null;
        ApplicationInfo.show(new LoginViewWindow());
    }

    @FXML
    public void isbnSearch(){
        String isbn = isbnSearchTxtField.getText();
        if(isbn.isEmpty()){
            LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE,"ISBN cannot be empty");
        }
        CheckoutRecord checkoutRecord = checkoutService.searchByIsbn(isbn.toLowerCase());
        if(checkoutRecord==null){
            LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE,"Book Not Found");
            return;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN);
        rowData = new OverdueController
                .RowData(
                        checkoutRecord.getLibraryMember().getFirstName()+" "+checkoutRecord.getLibraryMember().getLastName()
        ,checkoutRecord.getBookCopy().getBook().getIsbn()
        ,checkoutRecord.getBookCopy().getBook().getTitle()
        ,checkoutRecord.getBookCopy().getBookCopyNumber()
        ,checkoutRecord.getDueDateTime().format(dateTimeFormatter));

        overdueTable.setItems(FXCollections.observableArrayList(rowData));
    }
    public class RowData {
        String isbn;
        String title;
        String copyNo;
        String libraryMember;
        String dueDate;

        public RowData(String libraryMember, String isbn, String title, String copyNo,String dueDate){
            this.isbn = isbn;
            this.title = title;
            this.copyNo =copyNo;
            this.dueDate = dueDate;
            this.libraryMember = libraryMember;
        }

        public String getIsbn() {
            return isbn;
        }



        public String getTitle() {
            return title;
        }



        public String getCopyNo() {
            return copyNo;
        }



        public String getLibraryMember() {
            return libraryMember;
        }


        public String getDueDate() {
            return dueDate;
        }

    }
}
