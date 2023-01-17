import java.net.*;
import java.io.*;

public class Client {
    public static void main(String args[]) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost", 6666);
        } catch (UnknownHostException e) {
            System.out.println("Nieznany host.");
            System.exit(-1);
        } catch (ConnectException e) {
            System.out.println("Połączenie odrzucone.");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Błąd wejścia-wyjścia: " + e);
            System.exit(-1);
        }
        System.out.println("Połączono z " + clientSocket);
        //deklaracje zmiennych strumieniowych
        String line;
        BufferedReader brSockInp;
        BufferedReader brLocalInp;
        DataOutputStream socketOut;
        InputStreamReader socketIn;
        InputStreamReader localIn;
        //utworzenie strumieni
        try {
            socketOut = new DataOutputStream(clientSocket.getOutputStream());
            socketIn = new InputStreamReader(clientSocket.getInputStream());
            brSockInp = new BufferedReader(in);
            brLocalInp = new BufferedReader(localIn);
        } catch (IOException e) {
            System.out.println("Błąd przy tworzeniu strumieni: " + e);
            System.exit(-1);
        }
        //pętla główna klienta
        while (true) {
            try {
                line = brLocalInp.readLine();
                if (line != null) {
                    System.out.println("Wysyłam: " + line);
                    socketOut.writeBytes(line + '\n');
                    socketOut.flush();
                }
                if (line == null || "quit".equals(line)) {
                    System.out.println("Kończenie pracy...");
                    clientSocket.close();
                    System.exit(0);
                }
                line = brSockInp.readLine();
                System.out.println("Otrzymano: " + line);
            }
            catch(IOException e){
                System.out.println("Błąd wejścia-wyjścia: " + e);
                System.exit(-1);
            }
        }
    }
}
