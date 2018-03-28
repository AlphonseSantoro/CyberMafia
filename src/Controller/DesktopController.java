package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class DesktopController {

    @FXML
    MenuItem textEditor;
    @FXML
    StackPane centerLayout;

    @FXML
    private void openTextEditor() throws IOException {
        addLayoutToCenter(createScene(400, 500, "../Scene/Programs/TextEditor.fxml").getRoot());
    }

    private Scene createScene(int width, int height, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        return new Scene(root, width, height);
    }

    private void addLayoutToCenter(Node node){
        node.prefHeight(400);
        node.prefWidth(500);
        centerLayout.getChildren().add(node);
    }
}
