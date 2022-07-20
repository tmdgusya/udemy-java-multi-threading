package section2;

import java.math.BigInteger;

public class Example06 {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(BigInteger.valueOf(2000000), BigInteger.valueOf(1000000000)));

        thread.start();

        thread.interrupt(); // not working
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base+"^"+power+" = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                /*
                 * For-loop is hotspot in this code. So, we should check that thread was interrupted by other thread.
                 * If Current Thread is interrupted, then exit program
                 */
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
