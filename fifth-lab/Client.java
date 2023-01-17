import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws RemoteException {
        Client c = new Client();
        c.connectRemote();
    }

    private void connectRemote() {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 4444);
            Adder ad = (Adder) reg.lookup("hi, server!");
            System.out.println("wynik: " + ad.add(34, 35));
        } catch (NotBoundException | RemoteException e) {
            System.out.println(e);
        }
    }
}
