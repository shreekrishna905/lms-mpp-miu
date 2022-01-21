package com.lms.ui;

import com.lms.ApplicationStartUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class OverdueWindow  extends Stage implements Window {

    private FXMLLoader fxmlLoader;

    public OverdueWindow() {
        init();
    }

    @Override
    public void init() {
        try {
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("overdue-list.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 710, 480);
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);
            setTitle("Overdue");
            setResizable(false);
        } catch (IOException exception) {

        }
    }
}