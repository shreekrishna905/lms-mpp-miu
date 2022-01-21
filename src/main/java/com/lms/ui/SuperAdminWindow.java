package com.lms.ui;

import com.lms.ApplicationStartUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SuperAdminWindow extends Stage implements Window {

    public static SuperAdminWindow INSTANCE = new SuperAdminWindow();
    FXMLLoader fxmlLoader;

    public SuperAdminWindow(){
        init();
    }

    @Override
    public void init() {
        try{
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("super-user.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 690, 480);
            setScene(scene);
            setTitle("Super Admin");
            setResizable(false);
        } catch (IOException exception){

        }
    }
}
