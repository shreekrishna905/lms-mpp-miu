package com.lms;

import com.lms.ui.BookViewWindow;
import com.lms.ui.CheckoutViewWidow;
import com.lms.ui.LoginViewWindow;
import com.lms.ui.MemberViewWindow;
import com.lms.utils.ApplicationInfo;
import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStartUp extends Application {


    @Override
    public void start(Stage stage) {
        stage = new BookViewWindow();
        ApplicationInfo.currentStage = stage;
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}