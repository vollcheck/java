import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.TreeMap;


public interface Kilometers extends Remote {
    public TreeMap<Integer, String> sortByKilometers
            (TreeMap<Integer, String> tmap) throws RemoteException;

}