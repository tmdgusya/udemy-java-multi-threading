package section2;

public class Example03 {
    public static void main(String[] args) {
        Thread thread = new NewThread();

        thread.start();
    }

    /**
     * Thread Inheritance 를 이용하면 this 를 이용해서 현재 쓰레드객체에 직접 접근 가능.
     */
    private static class NewThread extends Thread {
        @Override
        public void run() {
            // 여기에 어떤 코드를 넣던 실행할때는 OS 가 새로운 스레드로 실행시켜주도록 스케쥴링함.
            System.out.println("We are now in Thread " + this.getName());
            System.out.println("Current Thread priority is " + this.getPriority());
        }
    }
}
