package com.lms.controller;

import com.lms.dataaccess.DataAccessFacade;
import com.lms.service.LoginService;
import com.lms.service.LoginServiceImpl;
import com.lms.ui.LoginViewWindow;
import com.lms.utils.ApplicationInfo;
import com.lms.utils.Constants;
import com.lms.exception.InvalidCredentialException;
import com.lms.utils.LmsDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginViewController {

    private LoginService loginService = new LoginServiceImpl(new DataAccessFacade());

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    public void login(ActionEvent event){
        boolean isInvalid = loginService.isInvalid(txtUserName.getText(),txtPassword.getText());
        if(isInvalid){
            LmsDialog.infoBox(Alert.AlertType.ERROR,Constants.ERROR_TITLE,Constants.LOGIN_EMPTY_INFO);
            return;
        }
        try{
            ApplicationInfo.currentAuth = loginService.login(txtUserName.getText(),txtPassword.getText());
        } catch (InvalidCredentialException ex){
            LmsDialog.infoBox(Alert.AlertType.ERROR,Constants.ERROR_TITLE,ex.getMessage());
            return;
        }
        LoginViewWindow.loginSuccessful(ApplicationInfo.currentAuth);
    }

}
