package com.lms.utils;

import javafx.scene.control.Alert;

public class LmsDialog {

    public static void infoBox(Alert.AlertType alertType, String titleBar, String headerMessage) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.showAndWait();
    }

}
