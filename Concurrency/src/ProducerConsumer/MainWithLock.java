package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MainWithLock {
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<String>();
        ReentrantLock bufferLock = new ReentrantLock();
        MyProducerWithLock producer = new MyProducerWithLock(buffer, ThreadColor.ANSI_YELLOW, bufferLock);
        MyConsumerWithLock consumer1 = new MyConsumerWithLock(buffer, ThreadColor.ANSI_PURPLE, bufferLock);
        MyConsumerWithLock consumer2 = new MyConsumerWithLock(buffer, ThreadColor.ANSI_CYAN, bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}

class MyProducerWithLock implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducerWithLock(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num : nums) {
            try {
                System.out.println(color + "Adding..." + num);

                bufferLock.lock();
                try{
                    buffer.add(num);
                }finally{
                    bufferLock.unlock();
                }



                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        System.out.println(color + "Adding EOF and exiting...");

        bufferLock.lock();
        try{
            buffer.add("EOF");
        }finally{
            bufferLock.unlock();
        }


    }
}

class MyConsumerWithLock implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumerWithLock(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        while (true) {
            bufferLock.lock();
            try {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals("EOF")) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            }finally{
                bufferLock.unlock();
            }
        }
    }
}