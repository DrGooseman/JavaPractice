package Thread_Runnable;


import static Thread_Runnable.ThreadColor.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(
                ANSI_RED
                        +"Hello from the main thread.");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("ANOTHER THREAD");
        anotherThread.start();

        new Thread() {
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from the anonymous class thread");
            }
        }.start();

        Thread myRunnableThread = new Thread(new MyRunnable(){
            @Override
            public void run() {
                super.run();
                try{
                   // waits 2000 ms and then takes over, anotherThread.join(2000);
                    anotherThread.join();
                    System.out.println(ANSI_RED + "Another thread done or timed out, so now im running again.");
                }catch(InterruptedException e){
                    System.out.println(ANSI_RED + "Couldn't wait, was interrupted");
                }
            }
        });
        myRunnableThread.start();
//        anotherThread.interrupt();

        System.out.println(ANSI_PURPLE+"Hello again from the main thread.");


    }
}
