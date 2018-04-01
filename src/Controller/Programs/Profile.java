package Controller.Programs;

import cybermafia.Server;
import cybermafia.UserHandling;
import cybermafia.main;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class Profile {
    @FXML Text ipAddress;
    @FXML Text cpu;
    @FXML Text gpu;
    @FXML Text hdd;

    public void initialize() throws IOException, ClassNotFoundException {
        Server server = new Server(main.getHost(), main.getPort());
        UserHandling user = server.loadUserProfile();
        ipAddress.setText(user.getIP());
        cpu.setText(user.getCpu());
        gpu.setText(user.getGpu());
        hdd.setText(user.getHdd());
    }
}
