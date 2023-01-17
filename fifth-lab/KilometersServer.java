import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
public class KilometersServer extends UnicastRemoteObject implements Kilometers {
    public KilometersServer() throws RemoteException {
        super();
    }

    @Override
    public TreeMap<Integer, String> sortByKilometers(TreeMap<Integer, String> tmap) throws RemoteException {
        Set<Entry<Integer, String>> set = tmap.entrySet();
        for (Object o : set) {
            Entry entry = (Entry) o;
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
        return tmap;
    }

    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(4444);
            reg.rebind("2 server", new KilometersServer());
            System.out.println("server is ready!");
        } catch (RemoteException e) {
            System.out.println("Exception" + e);
        }
    }
}
