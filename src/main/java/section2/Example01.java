package section2;

public class Example01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 여기에 어떤 코드를 넣던 실행할때는 OS 가 새로운 스레드로 실행시켜주도록 스케쥴링함.
            System.out.println("We are now in Thread : " + Thread.currentThread().getName());
        });

        System.out.println("Current Thread : " + Thread.currentThread().getName() + " before starting a new Thread");
        thread.start();
        System.out.println("Current Thread : " + Thread.currentThread().getName() + " after starting a new Thread");

        // 이 시간이 다 지날때까지는 Thread 를 스케쥴링 하지 말아달라고 OS 에 요청 - 이때 동안 이 Thread 는 CPU 를 사용하지 않음.
        Thread.sleep(1000L);
    }
}
