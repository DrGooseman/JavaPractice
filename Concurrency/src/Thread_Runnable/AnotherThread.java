package Thread_Runnable;

import static Thread_Runnable.ThreadColor.*;

public class AnotherThread extends Thread {

    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
           System.out.println(ANSI_BLUE + "Another thread woke me up");
        }
        System.out.println(ANSI_BLUE + "We are now awake");

    }
}
