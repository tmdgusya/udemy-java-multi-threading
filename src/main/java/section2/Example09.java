package section2;

public class Example09 {

    public static void main(String[] args) throws InterruptedException {
        DataSource dataSource = new DataSource();
        Thread thread = new Thread(new DataBaseQueryTask(dataSource));

        thread.start();

        thread.interrupt(); // interrupt thread

        Thread.sleep(1000);

        System.out.println("Is Active dataSource resources : " + dataSource.isActiveConnection); // false
    }

    private static class DataBaseQueryTask implements Runnable {

        private DataSource dataSource;

        public DataBaseQueryTask(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public void run() {
            try {
                dataSource.query();
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static class DataSource {

        private boolean isActiveConnection = false;

        public void query() throws IllegalAccessException {
            if (isActiveConnection) {
                throw new IllegalAccessException("This Connection use by other");
            }
            isActiveConnection = true;
            System.out.println("Query Start, Connection Open = " + isActiveConnection);
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                rollback();
                System.out.println("[RollBack] : " + e.getMessage());
            }
        }

        public void commit() {
            this.isActiveConnection = false;
        }

        public void rollback() {this.isActiveConnection = false;}

    }
}
