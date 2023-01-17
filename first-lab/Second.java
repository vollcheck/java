import java.net.*;
import java.io.*;

public class Second {
    public static void main(String args[]) {
        String[] pages = {"www.wp.pl", "www.ug.gda.pl", "www.onet.pl", "www.interia.pl"};

        Socket socket = null;
        for (String page : pages) {
            try {
                socket = new Socket(page, 80);
            } catch (UnknownHostException | IOException e) {
                System.out.println(e);
            }

            if (socket != null) {
                System.out.println("Summary about " + page);
                System.out.println("IP of web page: " + socket.getInetAddress());
                System.out.println("Port: " + socket.getPort());
                System.out.println("Local port: " + socket.getLocalPort());
                System.out.println();
            }
        }
    }
}
