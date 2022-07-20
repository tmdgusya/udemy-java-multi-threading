package section2;

import java.math.BigInteger;

public class Example07 {

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadSleepTask());

        thread.start();
    }

    private static class ThreadSleepTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
