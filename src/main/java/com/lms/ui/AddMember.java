package com.lms.ui;

import com.lms.ApplicationStartUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddMember extends Stage implements Window {

    FXMLLoader fxmlLoader;

    public AddMember(){
        init();
    }

    @Override
    public void init() {
        try{
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("add-member.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 418, 530);
            initModality(Modality.APPLICATION_MODAL);
            setScene(scene);
            setResizable(false);
        } catch (IOException exception){

        }
    }
}
