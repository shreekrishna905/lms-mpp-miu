package com.lms.controller;

import com.lms.business.LibraryMember;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.exception.InvalidMemberException;
import com.lms.service.MemberService;
import com.lms.service.MemberServiceImpl;
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


public class MemberViewController extends MenuController implements Initializable {

    private MemberService memberService = new MemberServiceImpl(new DataAccessFacade());

    private final ObservableList<LibraryMember> memberList =
            FXCollections.observableArrayList(memberService.getAll().values()
            );

    @FXML
    private TableView membersTable;

    @FXML
    private TableColumn memberId;

    @FXML
    private TableColumn firstName;

    @FXML
    private TableColumn lastName;

    @FXML
    private TableColumn phoneNumber;

    @FXML
    private TableColumn address;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button btnMember;

    @FXML
    private Button btnSearch;
    @FXML
    private Button btnCheckout;
    @FXML
    private Button btnBook;

    @FXML
    private Button btnOverdue;


    @FXML
    private void addMemberRoute(ActionEvent event) {
        ApplicationInfo.show(new AddMember());
    }

    @FXML
    private void searchMember() {
        String keyWord = searchTextField.getText();
        try {
            Validator.validateSearchKeyWord(keyWord);
            ObservableList<LibraryMember> list =FXCollections.observableArrayList(memberService.search(keyWord.toLowerCase()));
            membersTable.setItems(list);
        }catch(InvalidMemberException e){
            LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE,e.getMessage());
            return;
        }
    }

    public void loadMembersInTable() {
        memberList.forEach(member -> {
            memberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));

        });
        membersTable.setItems(memberList);
    }

    @FXML
    public void logout(ActionEvent event){
        ApplicationInfo.currentAuth = null;
        ApplicationInfo.show(new LoginViewWindow());
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

    @FXML
    public void showBook(ActionEvent event){
        ApplicationInfo.show(new BookViewWindow());
    }

}
