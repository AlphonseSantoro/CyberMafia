package Controller.Programs;

import cybermafia.Player;
import cybermafia.Server;
import cybermafia.UserHandling;
import cybermafia.main;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class Profile {
    private String userToDisplay;
    private UserHandling user;
    @FXML Text ipAddress;
    @FXML Text cpu;
    @FXML Text gpu;
    @FXML Text hdd;
    private Server server;

    public void initialize() throws IOException, ClassNotFoundException {
        loadProfile();
    }

    public void setUserToDisplay(String ip){
        this.userToDisplay = ip;
    }

    public String getUserToDisplay(){
        return this.userToDisplay;
    }

    public void setUser(UserHandling user){
        this.user = user;
    }

    public void loadProfile() throws IOException, ClassNotFoundException {
        server = new Server(main.getHost(), main.getPort());
        if(user == null){
            userToDisplay = Player.getCurrentUser();
        }
        user = server.loadUserProfile(userToDisplay);
        ipAddress.setText(user.getIP());
        cpu.setText(user.getCpu());
        gpu.setText(user.getGpu());
        hdd.setText(user.getHdd());

    }
}
