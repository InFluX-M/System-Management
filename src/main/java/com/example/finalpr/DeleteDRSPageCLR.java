package com.example.finalpr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.finalpr.HelloApplication.documentRegistrationSystem;

public class DeleteDRSPageCLR {

    @FXML
    private TextField DocumentRegistrationCode;

    @FXML
    private Label statue;

    @FXML
    void Delete() {

        if(documentRegistrationSystem.deleteEstate(DocumentRegistrationCode.getText())){
            statue.setText("Estate Deleted Successfully... :)");
        }
    }

    @FXML
    void back(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DocumentRegistrationSystemPage.fxml")));
            Stage s1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            s1.setScene(scene);
            s1.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
