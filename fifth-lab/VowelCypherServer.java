import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class VowelCypherServer extends UnicastRemoteObject implements VowelCypher {
    public VowelCypherServer() throws RemoteException {
        super();
    }

    @Override
    public String encrypt() throws RemoteException {
        return encrypt(null);
    }

    @Override
    public String encrypt(String s) throws RemoteException {
        StringBuilder result = new StringBuilder();
        char tmp = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case 'a', 'e', 'i', 'o', 'u' -> result.append(ch);
                default -> {
                    result.append(tmp);
                    tmp = ch;
                }
            }
        }
        result.append(tmp);
        return result.toString();
    }

    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(4444);
            reg.rebind("3 server", new VowelCypherServer());
            System.out.println("server is ready!");
        } catch (RemoteException e) {
            System.out.println("Exception" + e);
        }
    }
}
