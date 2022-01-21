package com.lms.ui;

import com.lms.ApplicationStartUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchCheckoutWindow extends Stage implements Window {

    public static SearchCheckoutWindow INSTANCE = new SearchCheckoutWindow();

    FXMLLoader fxmlLoader;

    public SearchCheckoutWindow(){
        init();
    }

    @Override
    public void init() {
        try{
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("checkout-search.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 693, 480);
            setScene(scene);
            setTitle("Search");
            setResizable(false);
        } catch (IOException exception){

        }
    }

}
