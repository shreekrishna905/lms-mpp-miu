package com.lms.controller;

import com.lms.ui.AddBookWindow;
import com.lms.ui.CheckoutWindow;
import com.lms.ui.LoginViewWindow;
import com.lms.ui.MemberViewWindow;
import com.lms.utils.ApplicationInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuperAdminController {


    @FXML
    public void showMemberWindow(ActionEvent event){
        ApplicationInfo.show(new MemberViewWindow());
    }

    @FXML
    public void showCheckoutWindow(ActionEvent event){
        ApplicationInfo.show(new CheckoutWindow());
    }

    @FXML
    public void showBookWindow(ActionEvent event){
        ApplicationInfo.show(new AddBookWindow());
    }

    @FXML
    public void logout(ActionEvent event){
        ApplicationInfo.currentAuth = null;
        ApplicationInfo.show(new LoginViewWindow());
    }



}
