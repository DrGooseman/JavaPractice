import java.util.Collections;

public class Main {

    public static void main(String[] args) {
	    Theatre theatre = new Theatre("Olympian", 8, 12);
       // theatre.getSeats();
        if(theatre.reserveSeat("B12")) {
            System.out.println("Please pay");
        } else {
            System.out.println("Sorry, seat is taken");
        }

        System.out.println("SORT BY PRICE");

        theatre.getSeatsByPrice();


        /// or new way

        theatre.getSeatsByPriceNewWay();







//        if(theatre.reserveSeat("H11")) {
//            System.out.println("Please pay");
//        } else {
//            System.out.println("Sorry, seat is taken");
//        }
    }
}
