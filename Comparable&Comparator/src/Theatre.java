import java.math.BigDecimal;
import java.util.*;


public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>();

    static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
           if (seat1.getPrice() < seat2.getPrice()){
               return -1;
           } else if (seat1.getPrice() > seat2.getPrice()){
               return 1;
           } else{
               return 0;
           }
        }
    };

    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;

        int lastRow = 'A' + (numRows -1);
        for (char row = 'A'; row <= lastRow; row++) {
            for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                double price = 12.00;

                if ((row < 'D') && (seatNum >=4 && seatNum <=9)){
                    price = 14.00;
                } else if ((row > 'F') || (seatNum <4 || seatNum >9)){
                    price = 7.00;
                }
                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    public boolean reserveSeat(String seatNumber) {

        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat);

        if (foundSeat >= 0){
            return seats.get(foundSeat).reserve();
        }
        else{
            System.out.println("There is no seat " + seatNumber);
            return false;
        }

    }




    // for testing
    public void getSeats() {
        for(Seat seat : seats) {
            System.out.println(seat.getSeatNumber() + " $" + seat.getPrice());
        }
    }

    public void getSeatsByPrice() {
        List<Theatre.Seat> priceSeats = new ArrayList<>(seats);
        Collections.sort(priceSeats, PRICE_ORDER);

        for(Seat seat : priceSeats) {
            System.out.println(seat.getSeatNumber() + " $" + seat.getPrice());
        }
    }

    public void getSeatsByPriceNewWay() {
        List<Theatre.Seat> priceSeats = new ArrayList<>(seats);



        Collections.sort(priceSeats, (a, b)->
                Comparator.comparing(Seat::getPrice)
                        .reversed().thenComparing(Seat::getSeatNumber)
                        .compare(a ,b));

//        String[] strings = {"hi", "bi"};
//
//        Arrays.sort(strings, new Comparator<String>() {
//            @Override
//            public int compare(String a1, String a2) {
//                BigDecimal a = new BigDecimal(a1);
//                BigDecimal b = new BigDecimal(a2);
//                return a.compareTo(b);
//            }
//        });
//
//        Arrays.sort(strings, (String a, String b) -> {
//            BigDecimal bd1 = new BigDecimal(a);
//            return bd1.compareTo(new BigDecimal(b));
//        });
//
//        Arrays.sort(strings, (a,b) ->
//                new BigDecimal(a).compareTo(new BigDecimal(b)));

        System.out.println("getSeatsByPriceNewWay");
        for(Seat seat : priceSeats) {
            System.out.println(seat.getSeatNumber() + " $" + seat.getPrice());
        }
    }

    private class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        public boolean reserve() {
            if(!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            } else {
                return false;
            }
        }

        public boolean cancel() {
            if(this.reserved) {
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            } else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }
    }



















}
