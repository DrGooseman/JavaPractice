package Function;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Employee[] names = {new Employee("James Quinn"), new Employee("Anna Quinn"), new Employee("John Doe")};

        Function<Employee, String> getLastName = (Employee employee) -> employee.getName().substring(employee.getName().indexOf(' ') + 1);

        System.out.println(getLastName.apply(names[0]));


        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
        Function chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(names[0]));
    }

}

class Employee{
    private String name;
    public Employee(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
