import java.net.URL;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class First {
    public static void main(String[] args) throws Exception {
        // sequential task
        URL otodom = new URL("https://www.otodom.pl/wynajem/mieszkanie/sopot/");
        BufferedReader in = new BufferedReader(new InputStreamReader(otodom.openStream()));
        ExecutorService es = Executors.newFixedThreadPool(5);
        Future<ArrayList<String>> future = es.submit(() -> extractLinks(in));
        ArrayList<String> result = null;

        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        saveToFile("links.txt", result);
        es.shutdown();
        in.close();
    }

    public static void saveToFile(String filename, ArrayList<String> content) throws IOException {
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(filename));
        for (String line : content) {
            outputWriter.write(line);
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }

    public static ArrayList<String> extractLinks(BufferedReader in) {
        ArrayList<String> result = new ArrayList<String>();

        String regex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        String line;
        Pattern p = Pattern.compile(regex);

        try {
            while ((line = in.readLine()) != null) {
                Matcher m = p.matcher(line);
                while (m.find()) {
                    result.add(m.group());
                }
            }
        } finally {
            return result;
        }
    }
}
