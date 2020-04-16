package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MainWithArrayBlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        MyProducerWithArrayBlockingQueue producer = new MyProducerWithArrayBlockingQueue(buffer, ThreadColor.ANSI_YELLOW);
        MyConsumerWithArrayBlockingQueue consumer1 = new MyConsumerWithArrayBlockingQueue(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumerWithArrayBlockingQueue consumer2 = new MyConsumerWithArrayBlockingQueue(buffer, ThreadColor.ANSI_CYAN);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_WHITE +"i'm being printed in the callable class");
                return "This is a callable result";
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}

class MyProducerWithArrayBlockingQueue implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducerWithArrayBlockingQueue(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num : nums) {
            try {
                System.out.println(color + "Adding..." + num);
               buffer.put(num);

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        System.out.println(color + "Adding EOF and exiting...");


        try{
            buffer.put("EOF");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

class MyConsumerWithArrayBlockingQueue implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyConsumerWithArrayBlockingQueue(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer){
            try {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.peek().equals("EOF")) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.take());
                }
            }catch(InterruptedException e){

            }}
        }
    }
}