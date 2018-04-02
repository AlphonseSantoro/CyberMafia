package Controller;

import cybermafia.Server;
import cybermafia.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import jfxtras.scene.control.window.CloseIcon;
import jfxtras.scene.control.window.MinimizeIcon;
import jfxtras.scene.control.window.Window;
import java.io.IOException;

public class DesktopController {

    @FXML MenuItem textEditor;
    @FXML Pane pane;
    @FXML ImageView backgroundIM, desktopIM;
    @FXML BorderPane borderPane;
    @FXML MenuBar menuBar;
    @FXML Text clock;

    private final Group group = new Group();

    public void initialize() throws IOException {
        // Set background image
        backgroundIM.fitWidthProperty().bind(pane.widthProperty());
        backgroundIM.fitHeightProperty().bind(pane.heightProperty());
        desktopIM.setFitWidth(50.0d);
        desktopIM.setFitHeight(50.0d);
        desktopIM.setLayoutX(10);
        desktopIM.setLayoutY(10);
        // Add group to pane
        pane.getChildren().add(group);
        Server server = new Server(main.getHost(), main.getPort());
        clock.setText(server.getUptime());
    }

    @FXML
    private void openTextEditor() throws IOException {
        openWindow(loadFXML(500, 400, "../Scene/Programs/TextEditor.fxml"), "New document", 500, 400);
    }

    @FXML
    private void openBrowser() throws IOException {
        openWindow(loadFXML(500, 400, "../Scene/Programs/Browser.fxml"), "Browser", 500, 400);
    }

    @FXML
    private void openProfile() throws IOException {
        openWindow(loadFXML(300, 200, "../Scene/Programs/Profile.fxml"), "My Computer", 300, 200);
    }

    private Node loadFXML(int width, int height, String fxml) throws IOException {
        Parent root = FXMLLoader.load((getClass().getResource(fxml)));
        return new Scene(root, width, height).getRoot();
    }

    private void openWindow(Node node, String title, int prefWidth, int prefHeight){
        Window window = new Window(title);
        window.setPrefSize(prefWidth, prefHeight);
        window.setLayoutX(10);
        window.setLayoutY(10);
        window.getLeftIcons().add(new CloseIcon(window));
        window.getLeftIcons().add(new MinimizeIcon(window));
        window.getContentPane().getChildren().add(node);
        window.setSelectable(true);
        window.setMovable(true);
        group.getChildren().add(window);
    }
}
