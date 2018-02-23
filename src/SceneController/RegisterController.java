package SceneController;

import cybermafia.Gui;
import cybermafia.Server;
import cybermafia.UserHandling;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {

    private String host;
    private int port;

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private PasswordField confirmPassword;
    @FXML private Button register;
    @FXML private TextField email;


    public void registerUser(){
        Gui gui = new Gui();
        register.setOnAction(event -> {
            if(!password.getText().equals(confirmPassword.getText())){
                gui.showAlertBox("Password", "Passwords does not match, try again.");
                password.setText("");
                confirmPassword.setText("");
            } else {
                UserHandling userHandling = new UserHandling();
                userHandling.setRegister(true);
                userHandling.setUsername(username.getText());
                userHandling.setPassword(password.getText());
                userHandling.setEmail(email.getText());
                Server server = null;
                try {
                    server = new Server(host, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server.sendSqlSelectStatementObject(host, port, userHandling);
                username.setText("");
                password.setText("");
                confirmPassword.setText("");
                email.setText("");
                Gui.changeScene(Gui.getLogInScene());
            }
        });
    }
}
