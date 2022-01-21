package com.lms.controller;

import com.lms.business.LibraryMember;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.service.MemberService;
import com.lms.service.MemberServiceImpl;
import com.lms.ui.AddMember;

import com.lms.utils.ApplicationInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class MemberViewController implements Initializable {

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
    private void addMemberRoute(ActionEvent event) {
        ApplicationInfo.show(new AddMember());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMembersInTable();
    }
}
