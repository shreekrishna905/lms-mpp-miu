package com.lms;

import com.lms.ui.CheckoutViewWidow;
import com.lms.ui.LoginViewWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStartUp extends Application {
    @Override
    public void start(Stage stage) {
        stage = new CheckoutViewWidow();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}