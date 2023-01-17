import java.util.TreeMap;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class KilometersClient {
    public static void main(String[] args) {
        KilometersClient c = new KilometersClient();
        c.connectRemote();
    }

    private void connectRemote() {
        TreeMap<Integer, String> tmap =
                new TreeMap<>();

        tmap.put(43, "Krakow");
        tmap.put(21, "Gdansk");
        tmap.put(32, "Gdynia");
        tmap.put(74, "Warszawa");
        tmap.put(87, "Wroclaw");

        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 4444);
            Kilometers ad = (Kilometers) reg.lookup("2 server");
            System.out.println("result: " + ad.sortByKilometers(tmap));
        } catch (NotBoundException | RemoteException e) {
            System.out.println(e);
        }
    }
}
