package com.lms.ui;

import com.lms.ApplicationStartUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckoutViewWidow extends Stage implements Window {

    private FXMLLoader fxmlLoader;

    public CheckoutViewWidow(){
        init();
    }

    @Override
    public void init() {
        try{
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("checkout.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);
            setTitle("Checkout");
            setResizable(false);
        } catch (IOException exception){

        }
    }
}
