package Controller;

import cybermafia.Gui;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import jfxtras.scene.control.window.CloseIcon;
import jfxtras.scene.control.window.MinimizeIcon;
import jfxtras.scene.control.window.Window;

import java.awt.*;
import java.io.IOException;

public class DesktopController {

    @FXML
    MenuItem textEditor;
    @FXML
    StackPane centerLayout;
    private final Group group = new Group();

    public void initialize(){
        centerLayout.getChildren().add(group);
        Pane p = new Pane();
        p.setMinSize(centerLayout.getMinWidth(), centerLayout.getMinHeight());
        ImageView im = new ImageView();
        Image i = new Image("/download.jpeg");
        im.setImage(i);
        p.setMinSize(Gui.getPrimaryStage().getWidth(), Gui.getPrimaryStage().getHeight());
        p.getChildren().add(im);
        group.getChildren().add(p);

    }

    @FXML
    private void openTextEditor() throws IOException {
        openWindow(createScene(400, 500, "../Scene/Programs/TextEditor.fxml").getRoot());
    }

    private Scene createScene(int width, int height, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        return new Scene(root, width, height);
    }

    private void openWindow(Node node){
        Window window = new Window("Window 1");
        window.setPrefSize(300, 200);
        window.setLayoutX(0);
        window.setLayoutY(10);
        window.getLeftIcons().add(new CloseIcon(window));
        window.getLeftIcons().add(new MinimizeIcon(window));
        window.getContentPane().getChildren().add(node);
        window.setSelectable(true);
        window.setMovable(true);
        window.setOnMousePressed(e -> {
            Point p = MouseInfo.getPointerInfo().getLocation();
            if(window.isSelected()){
                window.setLayoutX(p.getX() + window.getTranslateX());
                window.setLayoutY(p.getY() + window.getTranslateY());
            }
        });
        group.getChildren().add(window);
    }
}
