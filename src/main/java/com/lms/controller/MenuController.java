package com.lms.controller;

import com.lms.dataaccess.Auth;
import com.lms.utils.ApplicationInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController {

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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ApplicationInfo.currentAuth.toString().equals(Auth.ADMIN.toString())){
            btnCheckout.setManaged(false);
            btnSearch.setManaged(false);
            btnOverdue.setManaged(false);
        } else if(ApplicationInfo.currentAuth.toString().equals(Auth.LIBRARIAN.toString())){
            btnMember.setManaged(false);
            btnBook.setManaged(false);
        }
    }

}
