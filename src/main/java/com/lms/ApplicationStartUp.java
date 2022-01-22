package com.lms;

import com.lms.ui.*;
import com.lms.utils.ApplicationInfo;
import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStartUp extends Application {

    @Override
    public void start(Stage stage) {
        stage = new LoginViewWindow();
        ApplicationInfo.currentStage = stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}