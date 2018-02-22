package cybermafia;

import javafx.application.Application;

public class main {
    private static String host;
    private static int port;

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
