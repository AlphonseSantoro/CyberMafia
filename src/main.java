import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main {
    public static String host;
    public static int port;

    public static void main(String[] args){
        host = args[0];
        port = Integer.parseInt(args[1]);
        if (args.length != 2) {
            System.err.println(
                    "Usage: java CyberMafia <host name> <port number>");
            System.exit(1);
        }
        Application.launch(Gui.class);
    }

    public static String getHost(){
        return host;
    }

    public static int getPort(){
        return port;
    }

}
