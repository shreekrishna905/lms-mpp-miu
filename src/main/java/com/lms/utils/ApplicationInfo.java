package com.lms.utils;

import com.lms.dataaccess.Auth;
import com.lms.ui.MemberViewWindow;
import javafx.stage.Stage;

public class ApplicationInfo {

    public static Stage currentStage = null;
    public static Auth currentAuth = null;

    public static void show(Stage stage){
        Stage currentStage = ApplicationInfo.currentStage;
        currentStage.close();
        ApplicationInfo.currentStage = stage;
        stage.show();
    }

}
