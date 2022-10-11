import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// https://kodejava.org/how-do-i-create-port-scanner-program/

public class Fifth {
    public static Future<Optional<Integer>> openPort(final ExecutorService es,
                                         final String host,
                                         final int port, final int timeout) {
        return es.submit(new Callable<Optional<Integer>>() {
                @Override public Optional<Integer> call() {
                    try {
                        Socket socket = new Socket();
                        socket.connect(new InetSocketAddress(host, port), timeout);
                        socket.close();
                        return Optional.of(port);
                    } catch (Exception ex) {
                        return null;
                    }
                }
            });
    }

    public static void main(final String... args) {
        if (args.length > 1) {
            System.out.println("Cannot get the host to check");
            System.exit(0);
        }
        final String host = args[0];

        final ExecutorService es = Executors.newFixedThreadPool(20);
        final int timeout = 200;
        final List<Future<Integer>> futures = new ArrayList<>();
        for (int port = 1; port <= 65535; port++) {
            futures.add(openPort(es, host, port, timeout));
        }
        es.shutdown();

        for (final Future<Integer> f : futures) {
            System.out.println("There is opened port " + f + " on host " + host);
        }
    }
}
