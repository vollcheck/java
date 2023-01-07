import java.util.concurrent.locks.ReentrantLock;

public class First {
    public static int counter = 0;
    public static Object counterLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        return;
    }
}

class SynchronizingClass implements Runnable {
    public void run() {
        for (int i = 0; i< 10000; i++) {
            synchronized(First.counterLock) {
                First.counter++;
            }
        }
    }
}

class LockingClass implements Runnable {
    public void run() {
        ReentrantLock L = new ReentrantLock();
        L.lock();

        try {
            for (int i = 0; i< 10000; i++) {
                First.counter++;
            }
        } finally {
            L.unlock();
        }
    }
}
