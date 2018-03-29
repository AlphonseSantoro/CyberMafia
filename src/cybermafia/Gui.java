package cybermafia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Gui extends Application {
    private String host;
    private int port;
    private static Scene logInScene, registerScene, desktop;
    private static Stage primaryStage;



    @Override
    public void start(Stage primaryStage) throws IOException {
        host = main.getHost();
        port = main.getPort();
        Gui.primaryStage = primaryStage;

        // Create scenes
        logInScene =  createScene(400, 300, "../Scene/login.fxml");
        registerScene = createScene(400, 300, "../Scene/register.fxml");
        desktop = createScene(800, 600, "../Scene/desktop.fxml");

        // Setup primary stage with login scene
        primaryStage.setTitle("CyberMafia");
        primaryStage.setScene(logInScene);
        primaryStage.setOnCloseRequest(e -> Gui.primaryStage.close());
        primaryStage.show();
    }


    // TODO: public Scene getMainGameScene(Stage primaryStage, GridPane grid, BorderPane borderPane)

    /**
     * Displays an alertbox that always is on top.
     * One button to close the box.
     * @param title title of the alertbox
     * @param message the message to be displayed
     */
    public void showAlertBox(String title, String message){
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

    /**
     * Create a scene from an fxml file
     * @param width width of scene
     * @param height height of scene
     * @param fxml path to fxml file
     * @return
     * @throws IOException
     */
    private Scene createScene(int width, int height, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        return new Scene(root, width, height);
    }

    /**
     * Change current scene
     * @param scene scene to change to
     */
    public static void changeScene(Scene scene){
        primaryStage.setScene(scene);
    }

    /**
     * Get login scene
     * @return login scene
     */
    public static Scene getLogInScene(){
        return logInScene;
    }

    /**
     * Get register scene
     * @return register scene
     */
    public static Scene getRegisterScene(){
        return registerScene;
    }

    /**
     * Get desktop scene
     * @return desktop scene
     */
    public static Scene getDesktop(){
        return desktop;
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }
}
