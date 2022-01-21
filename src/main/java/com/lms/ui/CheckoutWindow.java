package com.lms.ui;

import com.lms.ApplicationStartUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckoutWindow extends Stage implements Window {

    public static CheckoutWindow INSTANCE = new CheckoutWindow();

    public CheckoutWindow(){
        init();
    }

    @Override
    public void init() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("checkout-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 693, 480);
            setScene(scene);
            setTitle("Checkout");
            setResizable(false);
        } catch (IOException exception){

        }
    }


}
