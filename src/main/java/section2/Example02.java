package section2;

public class Example02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Throw Exception");
            throw new IllegalArgumentException("TEST");
        });

        // DispatcherServlet 코드를 찾아보면 Thread Pool 에 이런 코드를 설정해두지 않았을까?
        // clean-up 과정을 여기서 하면 좋을듯 하다.
        thread.setUncaughtExceptionHandler((t, e) -> System.out.println("Occur Exception : " + t.getName() + " Error message is " + e.getMessage()));

        thread.start();

        thread.join();
    }
}
