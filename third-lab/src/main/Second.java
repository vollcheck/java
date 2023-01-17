import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Second {
    String result = "";

    public static void main(String[] args) throws IOException {
        Second threadSignaling = new Second();

        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // System.out.println(reader.readLine());

        Thread pub = new Thread(() -> threadSignaling.publisher());
        pub.start();

        Thread sub = new Thread(() -> threadSignaling.subscriber());
        sub.start();
    }

    public void subscriber() {
        synchronized (this) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                result = reader.readLine();
                System.out.println(Thread.currentThread().getName() + " . Message subscribed...");
                this.notifyAll();
            } catch (IOException e) {}
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void publisher() {
        synchronized (this) {
            try {
                while (result.length() == 0) {
                    this.wait();
                }
                System.out.println(Thread.currentThread().getName() + " . Message published...");
                System.out.println("Published result: " + result);
                this.notifyAll();
            } catch (InterruptedException e) {}
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
