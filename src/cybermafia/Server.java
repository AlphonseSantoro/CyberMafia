package cybermafia;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;

public class Server {
    private BufferedReader in;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;
    private UserHandling userHandling;


    public Server(String host, int portNumber) throws IOException {
        userHandling = new UserHandling();
        Socket serverSocket = new Socket(host, portNumber);
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        objIn = new ObjectInputStream(serverSocket.getInputStream());
        objOut = new ObjectOutputStream(serverSocket.getOutputStream());
    }

    public void sendSqlSelectStatementObject(UserHandling object){
        try {
            String fromServer = in.readLine();
            System.out.println("From Server: " + fromServer);
            objOut.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateUser(String username, String password) throws IOException, ClassNotFoundException {
            userHandling.setValidate(true);
            userHandling.setUsername(username);
            userHandling.setPassword(password);
            sendSqlSelectStatementObject(userHandling);
            UserHandling user = (UserHandling) objIn.readObject();
        return user.getAnswer();
    }

    public UserHandling loadUserProfile(String username) throws IOException, ClassNotFoundException {
        userHandling.setProfile(true);
        userHandling.setUsername(username);
        sendSqlSelectStatementObject(userHandling);
        return (UserHandling) objIn.readObject();
    }

    public UserHandling loadUserWithIP(String ip) throws IOException, ClassNotFoundException {
        userHandling.setIPadress(true);
        userHandling.setIp(ip);
        sendSqlSelectStatementObject(userHandling);
        return (UserHandling) objIn.readObject();
    }

    public UserHandling getUpTime(String username) throws IOException, ClassNotFoundException {
        userHandling.setUpTime(true);
        userHandling.setUsername(username);
        sendSqlSelectStatementObject(userHandling);
        return (UserHandling) objIn.readObject();
    }
}
