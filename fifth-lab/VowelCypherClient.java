import java.util.TreeMap;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class VowelCypherClient {
    public static void main(String[] args) {
        VowelCypherClient c = new VowelCypherClient();
        c.connectRemote();
    }

    private void connectRemote() {
        String s = "lokomotywa";

        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 4444);
            VowelCypher ad = (VowelCypher) reg.lookup("3 server");
            System.out.println("result: " + ad.encrypt(s));
        } catch (NotBoundException | RemoteException e) {
            System.out.println(e);
        }
    }
}
