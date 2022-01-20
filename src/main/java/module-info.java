module com.lms {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.lms.ui;
    exports com.lms.business;
    exports com.lms.controller;
    opens com.lms.ui to javafx.fxml;
    opens com.lms.business to javafx.fxml;
    opens com.lms.controller to javafx.fxml;
    opens com.lms to javafx.fxml;
    exports com.lms;
}