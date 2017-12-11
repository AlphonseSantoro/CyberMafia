import javax.swing.*;
import java.awt.*;

public class Gui {

    private JFrame frame;
    private JPanel panel;
    private Container contentPane;
    public Gui(){
        frame = new JFrame("CyberMafia");
        panel = new JPanel(new BorderLayout());
        panel.setBorder();
        contentPane = frame.getContentPane();
        makeLoggInForm();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public void makeLoggInForm(){
        JTextField userNameTextBox = new JTextField("Username");
        JPasswordField passwordField = new JPasswordField("Password");
        contentPane.add(userNameTextBox, BorderLayout())
        frame.add(userNameTextBox);
        frame.add(passwordField);
    }
}
