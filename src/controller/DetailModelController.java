
//Taken and updated from demo for Quiz 4

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Accountmodel;

public class DetailModelController {

    @FXML 
    private ResourceBundle resources;

    @FXML 
    private URL location;

    @FXML 
    private Button backButton; 

    @FXML
    private Label labelID; 

    @FXML 
    private Label labelName; 
    
    @FXML
    private Label labelEmail;
    
    @FXML
    private Label labelMember;

    @FXML 
    private ImageView image; 

    // this back button action serves to go back to the previous scene 
    @FXML
    void backButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (previousScene != null) {
            stage.setScene(previousScene);
        }

    }

    Accountmodel selectedModel;
    Scene previousScene;

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);

    }

    public void initData(Accountmodel model) {
        String member;
        
        if (model.getIsmember() == true) {
            member = "Yes";
        }else {
            member = "No";
            
        }
        
        selectedModel = model;
        labelID.setText(model.getAccountid().toString());
        labelName.setText(model.getAccountname());
        labelEmail.setText(model.getAccountemail());
        labelMember.setText(member);

        try {
            String imageName = "/resource/images/" + model.getAccountname() + ".png";
            Image profile = new Image(getClass().getResourceAsStream(imageName));
            image.setImage(profile);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert labelID != null : "fx:id=\"labelID\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert labelName != null : "fx:id=\"labelName\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert labelEmail != null : "fx:id=\"labelEmail\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert labelMember != null : "fx:id=\"labelMember\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        
        backButton.setDisable(true);
    }
}