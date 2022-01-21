package com.lms.ui;

import com.lms.ApplicationStartUp;
import com.lms.business.LibraryMember;
import com.lms.controller.MemberViewController;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.dataaccess.TestData;
import com.lms.service.MemberService;
import com.lms.service.MemberServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberViewWindow extends Stage implements Window {

    public static MemberViewWindow INSTANCE = new MemberViewWindow();


    FXMLLoader fxmlLoader;



    public MemberViewWindow(){
        init();
    }

    @Override
    public void init() {
        try{
            fxmlLoader = new FXMLLoader(ApplicationStartUp.class.getResource("member-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 693, 480);
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);
            setTitle("Member View");
            setResizable(false);
        } catch (IOException exception){

        }
    }


}
