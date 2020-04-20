package TwoPeople;

public class Main {


    public static void main(String[] args){
        Person bill = new Person("Bill");
        Person joe = new Person("Joe");

        bill.setNeighbor(joe);
        joe.setNeighbor(bill);

        new Thread(bill).start();
        new Thread(joe).start();
    }


}

class Person implements  Runnable{
     private  String name;
     private Person neighbor;

    public Person (String name){
        this.name = name;
    }

    public void setNeighbor(Person neighbor){
        this.neighbor = neighbor;
    }
    public  void sayHi(Person person){
        synchronized (this) {
            person.saidHi(this);
        }
    }

    public  void saidHi(Person person){
        System.out.println(person.name + "  said hi.");
        synchronized (this) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.saidHiBack(this);
        }
    }

    public  void saidHiBack(Person person){
        System.out.println(person.name + "  said hi back.");
    }

    @Override
    public void run() {
        sayHi(neighbor);
    }
}
