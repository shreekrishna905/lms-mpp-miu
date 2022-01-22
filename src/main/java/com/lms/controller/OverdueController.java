package com.lms.controller;

import com.lms.business.Book;
import com.lms.business.BookCopy;
import com.lms.business.CheckoutRecord;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.service.BookService;
import com.lms.service.BookServiceImpl;
import com.lms.service.CheckoutService;
import com.lms.service.CheckoutServiceImpl;
import com.lms.ui.CheckoutWindow;
import com.lms.ui.LoginViewWindow;
import com.lms.ui.SearchCheckoutWindow;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OverdueController {

    private CheckoutService checkoutService = new CheckoutServiceImpl(new DataAccessFacade());

    private BookService bookService = new BookServiceImpl(new DataAccessFacade());

    private List<RowData> rowDataList;

    @FXML
    TextField isbnSearchTxtField;

    @FXML
    TableView overdueTable;


    @FXML
    public void checkout(){
        ApplicationInfo.show(new CheckoutWindow());
    }

    @FXML
    public void showSearch(){ApplicationInfo.show(new SearchCheckoutWindow());}

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
        CheckoutRecord checkoutRecord = checkoutService.searchByIsbn(isbn);
        if(checkoutRecord==null){
            LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE,"Book Not Found");
            return;
        }
        List<BookCopy> books = checkoutRecord.getBookCopy().getBook().getBookCopies();
        addDataToTable(books,checkoutRecord);
        overdueTable.setItems(FXCollections.observableArrayList(rowDataList));
    }

    private void addDataToTable(List<BookCopy> books, CheckoutRecord checkoutRecord) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN);
        RowData rowData;
        rowDataList = new ArrayList<>();
        for(BookCopy copy : books){
            rowData = new OverdueController
                    .RowData(
                    checkoutRecord.getLibraryMember().getFirstName()+" "+checkoutRecord.getLibraryMember().getLastName()
                    ,copy.getBook().getIsbn()
                    ,copy.getBook().getTitle()
                    ,copy.getBookCopyNumber()
                    ,checkoutRecord.getDueDateTime().format(dateTimeFormatter)
                    ,OverdueController.CalulateFine.getFine(checkoutRecord.getDueDateTime()));
            rowDataList.add(rowData);
        }
    }

    public static class CalulateFine{
        public static String  getFine(LocalDateTime dueDate){
            long daysDue =  LocalDate.now().until(dueDate, ChronoUnit.DAYS);
            if(daysDue>0){
                return String.valueOf(0.25 * daysDue);
            }
            return String.valueOf(0);
        }
    }

    public class RowData {
        String isbn;
        String title;
        String copyNo;
        String libraryMember;
        String dueDate;
        String fine;

        public RowData(String libraryMember, String isbn, String title, String copyNo,String dueDate,String fine){
            this.isbn = isbn;
            this.title = title;
            this.copyNo =copyNo;
            this.dueDate = dueDate;
            this.libraryMember = libraryMember;
            this.fine = fine;
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

        public String getFine() {
            return fine;
        }
    }
}
