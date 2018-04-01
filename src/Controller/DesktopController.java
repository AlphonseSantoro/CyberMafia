package Controller;

import cybermafia.Gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import jfxtras.scene.control.window.CloseIcon;
import jfxtras.scene.control.window.MinimizeIcon;
import jfxtras.scene.control.window.Window;

import java.io.IOException;

public class DesktopController {

    @FXML MenuItem textEditor;
    @FXML Pane pane;
    @FXML BorderPane borderPane;
    private final Group group = new Group();

    public void initialize(){
        // Set background image
        ImageView im = new ImageView();
        Image i = new Image("/download.jpeg");
        im.setImage(i);
        im.fitWidthProperty().bind(pane.widthProperty());
        im.fitHeightProperty().bind(pane.heightProperty());
        // Add image and group to pane
        pane.getChildren().add(im);
        pane.getChildren().add(group);
    }

    @FXML
    private void openTextEditor() throws IOException {
        openWindow(loadFXML(500, 400, "../Scene/Programs/TextEditor.fxml"), "New document", 500, 400);
    }

    @FXML
    private void openBrowser() throws IOException {
        openWindow(loadFXML(500, 400, "../Scene/Programs/Browser.fxml"), "Browser", 500, 400);
    }

    private Node loadFXML(int width, int height, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        return new Scene(root, width, height).getRoot();
    }

    private void openWindow(Node node, String title, int prefWidth, int prefHeight){
        Window window = new Window(title);
        window.setPrefSize(prefWidth, prefHeight);
        window.setLayoutX(0);
        window.setLayoutY(10);
        window.getLeftIcons().add(new CloseIcon(window));
        window.getLeftIcons().add(new MinimizeIcon(window));
        window.getContentPane().getChildren().add(node);
        window.setSelectable(true);
        window.setMovable(true);
        group.getChildren().add(window);
    }
}
