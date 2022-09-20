package sync;

public class Example02 {

    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncreamentingThread increamentingThread = new IncreamentingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        increamentingThread.start();
        decrementingThread.start();

        increamentingThread.join();
        decrementingThread.join();

        System.out.println(inventoryCounter.getItems());
    }

    public static class DecrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }


    public static class IncreamentingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public IncreamentingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;

        //lock objects
        Object lock = new Object();

        // critical sections
        public void increment() {
            synchronized (lock) {
                items++;
            }
        }

        // critical sections
        public void decrement() {
            synchronized (lock) {
                items--;
            }
        }

        public synchronized int getItems() {
            return items;
        }

    }

}
