package com.lms.ui;

import com.lms.ApplicationStartUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberViewWindow extends Stage implements Window {


    FXMLLoader fxmlLoader;

    public MemberViewWindow(){
        init();
    }

    @Override
    public void init() {
        try{
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("member-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 418, 529);
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);
            setTitle("Member View");
            setResizable(false);
        } catch (IOException exception){

        }
    }
}
