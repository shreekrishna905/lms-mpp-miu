package com.lms.ui;

import com.lms.ApplicationStartUp;
import com.lms.dataaccess.Auth;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewWindow extends Stage implements Window{

    private FXMLLoader fxmlLoader;

    public LoginViewWindow(){
        init();
    }

    @Override
    public void init() {
        try{
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);
            setTitle("Login");
            setResizable(false);
        } catch (IOException exception){

        }
    }

    public void loginSuccessful(Auth currentAuth) {
        switch (currentAuth) {
            case ADMIN -> {
                BookViewWindow.INSTANCE.init();
            }
            case LIBRARIAN -> {
                MemberViewWindow.INSTANCE.init();
            }
            case BOTH -> AddBookWindow.INSTANCE.init();
        }
    }

}
