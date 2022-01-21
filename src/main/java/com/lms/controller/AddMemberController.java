package com.lms.controller;

import com.lms.business.Address;
import com.lms.business.LibraryMember;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.exception.InvalidMemberException;
import com.lms.service.MemberService;
import com.lms.service.MemberServiceImpl;
import com.lms.ui.BookViewWindow;
import com.lms.ui.MemberViewWindow;
import com.lms.utils.ApplicationInfo;
import com.lms.utils.Constants;
import com.lms.utils.LmsDialog;
import com.lms.utils.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AddMemberController {

    private MemberService memberService;

    @FXML
    private TextField memberId;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField state;

    @FXML
    private TextField zip;

    @FXML
    private TextField city;

    @FXML
    private TextField street;

    @FXML
    private Button addMemberBtn;

    public AddMemberController() {
        this.memberService = new MemberServiceImpl(new DataAccessFacade());
    }

    @FXML
    private void addMember(ActionEvent event) {
        try {
            LibraryMember member = populateMemberAndAddressValues();
            Validator.validateMember(member);
            memberService.addMember(member);
            LmsDialog.infoBox(Alert.AlertType.CONFIRMATION, Constants.CONFIRM_SUCCESS, Constants.CONFIRM_SUCCESS_MESSAGE);
            ApplicationInfo.show(new MemberViewWindow());
        } catch (InvalidMemberException e) {
            LmsDialog.infoBox(Alert.AlertType.WARNING, Constants.ERROR_TITLE, e.getMessage());
            return;
        }


    }

    private LibraryMember populateMemberAndAddressValues() {

        LibraryMember libraryMember = new LibraryMember(memberId.getText(), firstName.getText(), lastName.getText(), phoneNo.getText(),
                new Address(street.getText(), city.getText(), state.getText(), zip.getText()));
        return libraryMember;
    }
}