import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;

public class Server {
    private ResultSet rs;
    private PrintWriter out;
    private BufferedReader in;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;


    public Server(String host, int portNumber) throws IOException {
        Socket serverSocket = new Socket(host, portNumber);
        out = new PrintWriter(serverSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        objIn = new ObjectInputStream(serverSocket.getInputStream());
        objOut = new ObjectOutputStream(serverSocket.getOutputStream());
    }

    public void sendSqlSelectStatementObject(String host, int portNumber, UserHandling object){
        try {
            String fromServer = in.readLine();
            System.out.println("From Server: " + fromServer);
            objOut.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateUser(String host, int portNumber, String username, String password){
        try {
            //String fromServer = in.readLine();
            //System.out.println("From Server: " + fromServer);
            UserHandling userHandling = new UserHandling();
            userHandling.setValidate(true);
            userHandling.setUsername(username);
            userHandling.setPassword(password);
            sendSqlSelectStatementObject(host, portNumber, userHandling);
            UserHandling user = (UserHandling) objIn.readObject();
            String fromServer = user.getAnswer();
            if(fromServer.equals("true")){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
