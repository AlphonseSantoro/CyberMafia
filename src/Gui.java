import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Gui extends Application {
    GridPane gridPane;
    private UserHandling user;
    private Scene mainGame, loginScene;
    private GridPane logInLayout, mainGameLayout;
    private String host;
    private int port;

    public Gui(){

    }
    @Override
    public void start(Stage primaryStage) {
        host = main.getHost();
        port = main.getPort();

        // Setup primary stage
        primaryStage.setTitle("CyberMafia");


        mainGameLayout = createGridPane();
        mainGame = new Scene(mainGameLayout);

        // Create Log In Scene with Layout
        logInLayout = createGridPane();
        loginScene = new Scene(logInLayout, 400, 300);


        //Set start scene
        primaryStage.setScene(loginScene);

        // Create login Layout
        createLogInLayout(primaryStage, logInLayout);



        // Setup scene for login

        primaryStage.setOnCloseRequest(e -> primaryStage.close());
        primaryStage.show();
    }

    private GridPane createGridPane(){
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    private void createLogInLayout(Stage primaryStage, GridPane grid){
        // Create text fields
        Text usernameText = new Text("Username:");
        Text passwordText = new Text("Password:");
        TextField usernameField = new TextField();
        usernameField.setMaxSize(120, 20);
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxSize(120, 20);

        Button login = new Button("LogIn");
        System.out.println(usernameField.getText() + " " + passwordField.getText());
        login.setOnAction(e -> {
            Server server = new Server();
            boolean loginValidation = server.validateUser(host, port, usernameField.getText(), passwordField.getText());
            if(loginValidation){
                System.out.println("From server: Access Granted");
            }
            /*
            user = new UserHandling();
            if (user.validateUser(usernameField.getText(), passwordField.getText())) {
                primaryStage.setScene(mainGame);
            } else {
                showAlertBox("Log In", "Wrong username or password");
            } */
        });

        // Add elements to grid
        grid.add(usernameText, 1, 1);
        grid.add(passwordText, 1, 2);
        grid.add(usernameField, 2, 1);
        grid.add(passwordField, 2, 2);
        grid.add(login, 1, 3);
    }

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
