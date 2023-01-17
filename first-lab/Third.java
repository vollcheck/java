import java.net.*;

public class Third {
    public static void main(String args[]) {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            System.out.println("IP address of " + hostname + " is " + addr);
        } catch (UnknownHostException e) {
            System.out.println("Cannot get IP address for that machine.");
            System.exit(0);
        }
    }
}
