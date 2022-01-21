package com.lms.controller;

import com.lms.business.Address;
import com.lms.business.Author;
import com.lms.utils.ApplicationInfo;
import com.lms.utils.Constants;
import com.lms.utils.LmsDialog;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAuthorController {

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextArea txtShortBio;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtState;

    @FXML
    private TextField txtZip;

    private ObservableList<Author> authors;

    @FXML
    void addAuthor(ActionEvent event) {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String phoneNumber = txtPhoneNumber.getText().trim();
        String shortBio = txtShortBio.getText().trim();
        Address address = new Address(txtStreet.getText().trim(),txtCity.getText().trim(),txtState.getText().trim(),txtZip.getText().trim());
        if(isEmptyAddress(address) || isEmptyPersonalInfo(firstName,lastName,phoneNumber,shortBio)){
            LmsDialog.infoBox(Alert.AlertType.ERROR, Constants.ERROR_TITLE,Constants.BOOK_EMPTY_INFO);
            return;
        }
        Author author = new Author(firstName,lastName,phoneNumber,address,shortBio);
        authors.add(author);
        ApplicationInfo.closeStage(event);
    }

    public void setAuthors(ObservableList<Author> authors) {
        this.authors = authors;
    }


    private boolean isEmptyAddress(Address address){
        if(address.getCity().isEmpty() || address.getState().isEmpty() || address.getStreet().isEmpty() || address.getZip().isEmpty()){
            return true;
        }
        return false;
    }

    private boolean isEmptyPersonalInfo(String firstName,String lastName,String phoneNumber,String shortBio){
        if(firstName.isEmpty() || lastName.isEmpty() || shortBio.isEmpty()){
            return true;
        }
        return false;
    }

}
