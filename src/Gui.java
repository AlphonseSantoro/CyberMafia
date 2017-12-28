import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private JFrame frame;
    private JPanel panel;
    private Container contentPane;
    public Gui(){
        super("CyberMafia");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        makeLoggInForm();
    }

    public void makeLoggInForm(){
        JTextField userNameTextBox = new JTextField("Username");
        JPasswordField passwordField = new JPasswordField("Password");
        //contentPane.add(userNameTextBox, BorderLayout(BORDE));
        frame.add(userNameTextBox);
        frame.add(passwordField);
        int[] arr = new int[10];
        arr[0] = 5;
    }
}
