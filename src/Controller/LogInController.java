package Controller;

import cybermafia.Gui;
import cybermafia.Player;
import cybermafia.Server;
import cybermafia.main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInController {
    private String host;
    private int port;

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button logInBtn;
    @FXML private Button register;

    /**
     * When login button is pressed send data to server for validation, if user data is correct create a session for the user
     * TODO: Figure out how sessions work
     */
    public void validateUser() {
        host = main.getHost();
        port = main.getPort();
        this.logInBtn.setOnAction(e -> {
            Server server = null;
            try {
                server = new Server(host, port);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Gui g = new Gui();
            if (server == null) {
                g.showAlertBox("Error", "No connection to server.");
            } else {
                try {
                    if (server.validateUser(this.username.getText(), this.password.getText())) {
                        Player.setCurrentUser(this.username.getText());
                        this.username.setText("");
                        this.password.setText("");
                        Gui.changeScene(g.createScene(800, 600, "../Scene/desktop.fxml"));
                    } else {
                        g.showAlertBox("Log In", "Wrong username or password");
                        System.out.println("Wrong username...");
                    }
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /**
     * When register new user button is pressed change scene to register
     */
    public void registerUser(){
        register.setOnAction(event -> Gui.changeScene(Gui.getRegisterScene()));
    }
}
