package com.lms.controller;

import com.lms.business.CheckoutRecord;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.exception.EntityNotFoundException;
import com.lms.service.CheckoutService;
import com.lms.service.CheckoutServiceImpl;
import com.lms.utils.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class CheckoutController {

    private CheckoutService checkoutService = new CheckoutServiceImpl(new DataAccessFacade());

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TableView tblCheckout;

    private RowData rowData;

    @FXML
    public void print(ActionEvent event){
        if(this.rowData==null)
            return;
        System.out.format("%-15s%-15s%-15s%-15s\n", "MEMBER ID","ISBN", "CHECKOUT DATE", "DUE DATE");
        System.out.format("%-15d%-15s%-15s%-15s\n", rowData.getMemberId(), rowData.getIsbn(), rowData.getCheckoutDate(), rowData.getDueDate());
    }

    @FXML
    public void checkout(ActionEvent event){
        if(isInvalid(txtMemberId.getText().trim(),txtIsbn.getText().trim())){
            LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE,Constants.CHECKOUT_EMPTY_INFO);
            return;
        }
        CheckoutRecord checkoutRecord = null;
        try {
            checkoutRecord = checkoutService.checkout(txtMemberId.getText().trim(),txtIsbn.getText().trim() );
        } catch (EntityNotFoundException ex) {
            LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE, ex.getMessage());
            return;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN);
        rowData = new RowData(checkoutRecord.getLibraryMember().getMemberId(), checkoutRecord.getBookCopy().getBook().getIsbn(),checkoutRecord.getCheckoutDateTime().format(dateTimeFormatter),checkoutRecord.getDueDateTime().format(dateTimeFormatter));
        tblCheckout.setItems(FXCollections.observableArrayList(new RowData(checkoutRecord.getLibraryMember().getMemberId(),checkoutRecord.getBookCopy().getBook().getIsbn(),checkoutRecord.getCheckoutDateTime().format(dateTimeFormatter),checkoutRecord.getDueDateTime().format(dateTimeFormatter))));
    }


    private boolean isInvalid(String memberId, String isbn) {
        return memberId.trim().isEmpty() || isbn.trim().isEmpty();

    }

    public class RowData {
        String isbn;
        String memberId;
        String checkoutDate;
        String dueDate;

        public RowData(String memberId, String isbn, String checkoutDate,String dueDate){
            this.isbn = isbn;
            this.checkoutDate = checkoutDate;
            this.dueDate = dueDate;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getCheckoutDate() {
            return checkoutDate;
        }

        public String getDueDate() {
            return dueDate;
        }

        public String getMemberId() {
            return memberId;
        }
    }

}
