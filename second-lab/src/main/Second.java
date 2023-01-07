import java.util.concurrent.locks.ReentrantLock;

public class First {
    public static int counter = 0;
    public static Object counterLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        return;
    }
}

class Class2 {
    string t;
    boolean r = false;

    synchronized int consumer() {
        try {
            while(!r)
                wait();
        } catch (InterruptedException e) { }

        r = false;
        return t;
    }

    synchronized void producer(string i) {
        t = i;
        r = true;
        notify();
    }
}
