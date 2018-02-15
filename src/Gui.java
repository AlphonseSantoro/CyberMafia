import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Gui extends Application {
    GridPane gridPane;
    private String host;
    private int port;
    private Scene logInScene, registerScene, mainGameScene;

    @Override
    public void start(Stage primaryStage) {
        host = main.getHost();
        port = main.getPort();

        // Get scenes
        logInScene = getLoginScene(primaryStage, createGridPane(10, 10, Pos.CENTER), getBorderPane());
        registerScene = getRegisterScene(primaryStage, createGridPane(10, 10, Pos.CENTER), getBorderPane());
        // TODO: mainGameScene = getMainGameScene();


        // Setup primary stage with login scene
        primaryStage.setTitle("CyberMafia");
        primaryStage.setScene(logInScene);

        primaryStage.setOnCloseRequest(e -> primaryStage.close());
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
     * @param grid grid layout.
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
            Server server = new Server();
            boolean loginValidation = server.validateUser(host, port, usernameField.getText(), passwordField.getText());
            if (loginValidation) {
                System.out.println("From server: Access Granted");
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

    private Scene getRegisterScene(Stage primaryStage, GridPane grid, BorderPane borderPane){
        //Create text field
        Text usernameText = new Text("Username:");
        Text passwordText = new Text("Password:");
        Text confirmPasswordText = new Text("Confirm password:");

        //Create text boxes
        TextField username = new TextField();
        username.setMaxSize(120, 20);
        TextField password = new TextField();
        password.setMaxSize(120, 20);
        TextField confirmPassword = new TextField();
        confirmPassword.setMaxSize(120, 20);

        //Create Registerbutton
        Button register = new Button("Register");
        register.setOnAction(event -> {
            if(!password.getText().equals(confirmPassword.getText())){
                showAlertBox("Password", "Passwords does not match, try again.");
            } else {
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
        grid.add(confirmPasswordText, 0, 3);
        // Column 1
        grid.add(username, 1, 1);
        grid.add(password, 1, 2);
        grid.add(confirmPassword, 1, 3);
        grid.add(register, 1, 4);

        borderPane.setCenter(grid);
        borderPane.setBottom(loginButton);
        return new Scene(borderPane, 250, 250);
    }

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
