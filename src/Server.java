import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server {



    public Server(){

    }

    public boolean validateUser(String host, int portNumber, String username, String password){
        String hostName = host;
        try (
                Socket serverSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()))
        ){
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer = in.readLine();
            String fromUser;
            System.out.println("From Server: " + fromServer);
            out.println(username);
            out.println(password);
            if(in.readLine().equals("true")){
                System.out.println("Access granted");
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
