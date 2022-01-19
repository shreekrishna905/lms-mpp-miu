module com.lms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lms to javafx.fxml;
    exports com.lms;
}