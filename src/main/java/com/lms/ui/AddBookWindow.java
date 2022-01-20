package com.lms.ui;

import com.lms.ApplicationStartUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class AddBookWindow extends Stage implements Window {


    FXMLLoader fxmlLoader;

    public AddBookWindow(){
        init();
    }

    @Override
    public void init() {
        try{
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("add-book.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 523, 374);
            setScene(scene);
            setTitle("Add Book");
            setResizable(false);
        } catch (IOException exception){

        }
    }
}
