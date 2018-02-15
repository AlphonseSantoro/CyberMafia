import java.io.*;
import java.net.Socket;

public class Server {



    public Server(){

    }

    public void sendSqlStatementObject(String host, int portNumber, UserHandling object){
        try (
                Socket serverSocket = new Socket(host, portNumber);
                PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                ObjectInputStream objIn = new ObjectInputStream(serverSocket.getInputStream());
                ObjectOutputStream objOut = new ObjectOutputStream(serverSocket.getOutputStream())
        ){
            String fromServer = in.readLine();
            System.out.println("From Server: " + fromServer);
            objOut.writeObject(object);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateUser(String host, int portNumber, String username, String password){
        try (
                Socket serverSocket = new Socket(host, portNumber);
                PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()))
        ){
            //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer = in.readLine();
            System.out.println("From Server: " + fromServer);

            //out.println(username);
            //out.println(password);
            if(in.readLine().equals("true")){
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
