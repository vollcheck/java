import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VowelCypher extends Remote {
    String encrypt() throws RemoteException;

    String encrypt(String s) throws RemoteException;
}