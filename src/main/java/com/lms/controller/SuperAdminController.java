package com.lms.controller;

import com.lms.ui.*;
import com.lms.utils.ApplicationInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
        ApplicationInfo.show(new BookViewWindow());
    }

    @FXML
    public void showSearchWindow(ActionEvent event){ApplicationInfo.show(new SearchCheckoutWindow());}

    public void showOverdueWindow(ActionEvent event){ApplicationInfo.show(new OverdueWindow());}

    @FXML
    public void logout(ActionEvent event){
        ApplicationInfo.currentAuth = null;
        ApplicationInfo.show(new LoginViewWindow());
    }



}
