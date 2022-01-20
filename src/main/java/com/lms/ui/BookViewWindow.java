package com.lms.ui;

import com.lms.ApplicationStartUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class BookViewWindow extends Stage implements Window{


    FXMLLoader fxmlLoader;

    public BookViewWindow(){
        init();
    }

    @Override
    public void init() {
        try{
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("book-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 693, 480);
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);
            setTitle("Book View");
            setResizable(false);
        } catch (IOException exception){

        }
    }
}
