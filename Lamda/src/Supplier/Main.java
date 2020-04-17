package Supplier;

import java.util.Random;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
        for (int i = 0; i < 10; i++){
            System.out.println(randomSupplier.get());
        }

    }

}
