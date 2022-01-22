package com.lms.controller;

import com.lms.business.LibraryMember;
import com.lms.dataaccess.Auth;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.service.MemberService;
import com.lms.service.MemberServiceImpl;
import com.lms.ui.*;
import com.lms.utils.ApplicationInfo;
import com.lms.utils.Constants;
import com.lms.utils.LmsDialog;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SearchCheckoutController extends MenuController implements Initializable {

    private MemberService memberService = new MemberServiceImpl(new DataAccessFacade());

    @FXML
    private TextField txtMemberId;

    @FXML
    private TableView tblCheckout;

    List<CheckoutController.RowData> rows;

    @FXML
    public void search(ActionEvent event){
        String memberId = txtMemberId.getText().trim();
        LibraryMember member = memberService.findById(memberId);
        if(member==null){
            LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE,Constants.MEMBER_ID_NOT_EXIST);
            return;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DataAccessFacade.DATE_PATTERN);

        rows = member.getCheckoutRecords()
                .stream()
                .map(checkoutRecord -> new CheckoutController.RowData(checkoutRecord.getLibraryMember().getMemberId(), checkoutRecord.getBookCopy().getBook().getIsbn(), checkoutRecord.getCheckoutDateTime().format(dateTimeFormatter), checkoutRecord.getDueDateTime().format(dateTimeFormatter))).collect(Collectors.toList());
        if(rows.isEmpty()){
            LmsDialog.infoBox(Alert.AlertType.INFORMATION, Constants.INFO,Constants.NO_SEARCH_RECORD);
            return;
        }
        tblCheckout.setItems(FXCollections.observableArrayList(rows));
    }

    @FXML
    public void print(ActionEvent event){
        for(CheckoutController.RowData rowData: rows){
            System.out.format("%-15s%-15s%-15s%-15s\n", "MEMBER ID","ISBN", "CHECKOUT DATE", "DUE DATE");
            System.out.format("%-15s%-15s%-15s%-15s\n", rowData.getMemberId(),rowData.getIsbn(), rowData.getCheckoutDate(), rowData.getDueDate());
        }
    }

    @FXML
    public void showCheckout(ActionEvent event){
        ApplicationInfo.show(new CheckoutWindow());
    }

    @FXML
    public void showMemberWindow(ActionEvent event){
        ApplicationInfo.show(new MemberViewWindow());
    }

    @FXML
    public void showBookWindow(ActionEvent event){
        ApplicationInfo.show(new BookViewWindow());
    }

    @FXML
    public void showOverDues(){
        ApplicationInfo.show(new OverdueWindow());
    }

    @FXML
    public void logout(ActionEvent event){
        ApplicationInfo.currentAuth = null;
        ApplicationInfo.show(new LoginViewWindow());
    }

}
