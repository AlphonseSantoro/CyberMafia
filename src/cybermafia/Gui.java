package cybermafia;

import cybermafia.UserHandling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {
    GridPane gridPane;
    private String host;
    private int port;
    private Scene logInScene, registerScene, mainGameScene;
    private Stage primaryStage;



    @Override
    public void start(Stage primaryStage) throws IOException {
        host = main.getHost();
        port = main.getPort();
        this.primaryStage = primaryStage;

        // Get scenes
        //logInScene = getLoginScene(this.primaryStage, createGridPane(10, 10, Pos.CENTER), getBorderPane());
        registerScene = getRegisterScene(this.primaryStage, createGridPane(10, 10, Pos.CENTER), getBorderPane());
        // TODO: mainGameScene = getMainGameScene();

        /**
         * Testing FXML
         */
        Parent root = FXMLLoader.load(getClass().getResource("../Scene/login.fxml"));
        logInScene = new Scene(root, 400, 300);


        // Setup primary stage with login scene
        primaryStage.setTitle("CyberMafia");
        primaryStage.setScene(logInScene);

        primaryStage.setOnCloseRequest(e -> this.primaryStage.close());
        primaryStage.show();
    }

    /**
     * Returns a gridpane.
     * @param hGap horizontal gap
     * @param vGap vertical gap
     * @param pos position
     * @return
     */
    private GridPane createGridPane(int hGap, int vGap, Pos pos){
        gridPane = new GridPane();
        gridPane.setHgap(hGap);
        gridPane.setVgap(vGap);
        gridPane.setAlignment(pos);
        return gridPane;
    }

    /**
     * Returns a borderpane
     */
    private BorderPane getBorderPane(){
        return new BorderPane();
    }

    /**
     * Creates the login scene and returns it.
     * @param primaryStage primary stage
     * @param grid grid layout placed in center
     * @param borderPane borderpane for main layout
     * @return
     */
    private Scene getLoginScene(Stage primaryStage, GridPane grid, BorderPane borderPane){
        // Create text fields
        Text usernameText = new Text("Username:");
        Text passwordText = new Text("Password:");

        //Create text boxes
        TextField usernameField = new TextField();
        usernameField.setMaxSize(120, 20);
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxSize(120, 20);

        // Create new user button
        Button newUser = new Button("New user? Create Account");
        newUser.setOnAction(event -> primaryStage.setScene(registerScene));

        // Create login button with eventhandler
        Button login = new Button("LogIn");
        System.out.println(usernameField.getText() + " " + passwordField.getText());
        login.setOnAction(e -> {
            Server server = null;
            try {
                server = new Server(host, port);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            boolean loginValidation = server.validateUser(host, port, usernameField.getText(), passwordField.getText());
            if (loginValidation) {
                System.out.println("From server: Access Granted");
                //showAlertBox("Success", "Log in successful");
                usernameField.setText("");
                passwordField.setText("");
                primaryStage.setScene(mainGameScene);
            } else {
                showAlertBox("Log In", "Wrong username or password");
            }
        });

        // Add elements to grid
        grid.add(usernameText, 1, 1);
        grid.add(passwordText, 1, 2);
        grid.add(usernameField, 2, 1);
        grid.add(passwordField, 2, 2);
        grid.add(login, 1, 3);

        borderPane.setCenter(grid);
        borderPane.setBottom(newUser);

        return new Scene(borderPane, 250, 250);
    }

    /**
     * Creates the regiter scene and returns it.
     * @param primaryStage primary stage
     * @param grid grid layout placed in center
     * @param borderPane borderpane for main layout
     * @return
     */
    private Scene getRegisterScene(Stage primaryStage, GridPane grid, BorderPane borderPane){
        //Create text field
        Text usernameText = new Text("Username:");
        Text passwordText = new Text("Password:");
        Text confirmPasswordText = new Text("Confirm password:");
        Text emailText = new Text("Email:");

        //Create text boxes
        TextField username = new TextField();
        username.setMaxSize(120, 20);
        PasswordField password = new PasswordField();
        password.setMaxSize(120, 20);
        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setMaxSize(120, 20);
        TextField email = new TextField();
        email.setMaxSize(120, 20);

        //Create Register button
        Button register = new Button("Register");
        register.setOnAction(event -> {
            if(!password.getText().equals(confirmPassword.getText())){
                showAlertBox("Password", "Passwords does not match, try again.");
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
                primaryStage.setScene(logInScene);
            }
        });

        //Create button to return to login scene
        Button loginButton = new Button("Log in");
        loginButton.setOnAction(event -> primaryStage.setScene(logInScene));

        // Add elements to grid
        // Column 0
        grid.add(usernameText, 0, 1);
        grid.add(passwordText, 0, 2);
        grid.add(emailText, 0, 4);
        grid.add(confirmPasswordText, 0, 3);

        // Column 1
        grid.add(username, 1, 1);
        grid.add(password, 1, 2);
        grid.add(confirmPassword, 1, 3);
        grid.add(email, 1, 4);
        grid.add(register, 1, 5);

        borderPane.setCenter(grid);
        borderPane.setBottom(loginButton);
        return new Scene(borderPane, 250, 250);
    }

    // TODO: public Scene getMainGameScene(Stage primaryStage, GridPane grid, BorderPane borderPane)

    /**
     * Displays an alertbox that always is on top.
     * One button to close the box.
     * @param title title of the alertbox
     * @param message the message to be displayed
     */
    private void showAlertBox(String title, String message){
        Stage alertStage = new Stage();
        alertStage.setTitle(title);
        alertStage.setOnCloseRequest(e -> alertStage.close());
        alertStage.setAlwaysOnTop(true);

        Text textMessage = new Text(message);
        textMessage.setTextAlignment(TextAlignment.CENTER);
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> alertStage.close());
        okButton.setPadding(new Insets(5, 30, 5, 30));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20, 50, 20, 50));
        vBox.getChildren().addAll(textMessage, okButton);

        StackPane pane = new StackPane();
        pane.getChildren().add(vBox);
        pane.setAlignment(Pos.CENTER);

        alertStage.setScene(new Scene(pane));
        alertStage.show();
    }



}
