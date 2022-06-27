package com.example.finalpr;

import com.example.finalpr.Availabilities.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.finalpr.HelloApplication.civilRegistrationSystem;

public class DeleteCRSPageCLR implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField ID;

    @FXML
    private Label statue;

    @FXML
    void Delete(MouseEvent event) {
        civilRegistrationSystem.deletePerson(ID.getText());
        statue.setText("Person Deleted Successfully... :)");
    }

    @FXML
    void back(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CivilRegistrationSystemPage.fxml"));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException e) {

        }
    }

}
