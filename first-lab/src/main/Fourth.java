import java.net.*;

public class Fourth {
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("You need to pass host name");
            System.exit(0);
        }
        String host = args[0];
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.out.println("Cannot get the IP of given host - " + host);
            System.exit(0);
        }

        String ip = addr.getHostAddress();
        System.out.println("Address of host " + host + " is " + ip);
    }
}
