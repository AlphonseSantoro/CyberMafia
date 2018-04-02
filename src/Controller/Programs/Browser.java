package Controller.Programs;

import cybermafia.Server;
import cybermafia.UserHandling;
import cybermafia.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Browser {
    @FXML
    TextField ip;
    private Profile profileController;
    @FXML BorderPane borderPane;

    @FXML
    private void websiteAddress() throws IOException, ClassNotFoundException {
        Server server = new Server(main.getHost(), main.getPort());
        UserHandling user = server.loadUserWithIP(ip.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scene/Programs/Profile.fxml"));
        Parent root = new Scene(loader.load(), borderPane.getWidth(), borderPane.getHeight()).getRoot();
        profileController = loader.getController();
        profileController.setUserToDisplay(user.getUsername());
        profileController.loadProfile();
        borderPane.setCenter(root);
    }

    public Profile getProfileController() {
        return profileController;
    }

    public void setProfileController(Profile profileController) {
        this.profileController = profileController;
    }
}
