import java.io.IOException;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.locks.ReentrantLock;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class SynchronizingClass implements Runnable {
    public void run() {
        synchronized(FirstA.ratesLock) {
            try {
                Document doc = Jsoup
                    .connect("https://www.nbp.pl/home.aspx?f=/kursy/kursya.html")
                    .get();

                Elements tableBody = doc.select("#contentholder > table > tbody > tr");
                for (Element elem : tableBody) {
                    String[] splitted = elem.text().split("\\s+");
                    String currency = splitted[splitted.length-2];
                    String rate = splitted[splitted.length-1];
                    if (Arrays.stream(FirstA.interestingRates).anyMatch(currency::contains)) {
                        System.out.println(elem.text());
                    }
                }
            } catch (IOException e) {
                System.out.println("IOException occured!");
            }
        }
    }
}

class LockingClass implements Runnable {
    public void run() {
        ReentrantLock L = new ReentrantLock();
        L.lock();
        try {
            Document doc = Jsoup
                .connect("https://www.nbp.pl/home.aspx?f=/kursy/kursya.html")
                .get();

            Elements tableBody = doc.select("#contentholder > table > tbody > tr");
            for (Element elem : tableBody) {
                String[] splitted = elem.text().split("\\s+");
                String currency = splitted[splitted.length-2];
                String rate = splitted[splitted.length-1];
                if (Arrays.stream(FirstA.interestingRates).anyMatch(currency::contains)) {
                    System.out.println(elem.text());
                }
            }
        } catch (IOException e) {
            System.out.println("IOException occured!");
        } finally {
            L.unlock();
        }
    }
}

public class FirstA {
    public static String[] interestingRates = {"EUR", "USD", "CHF"};
    public static Object ratesLock = new Object();

    // using `synchronized` method
    public static void main(String[] args) throws IOException {
        System.out.println("SynchronizingClass output: ");
        SynchronizingClass sc = new SynchronizingClass();
        sc.run();

        System.out.println();
        System.out.println("LockingClass output: ");
        LockingClass lc = new LockingClass();
        lc.run();

    }
}
