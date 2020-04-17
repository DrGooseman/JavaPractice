package Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Billy Bob", 21);
        Employee jack = new Employee("Bob Cobb", 40);
        Employee snow = new Employee("Jane Doe", 22);
        Employee red = new Employee("Jack Bauer", 35);
        Employee charming = new Employee("James Quinn", 29);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "\nEmployees 30 and under", employee ->employee.getAge() <= 30);
        printEmployeesByAge(employees, "\nEmployees 25 and under", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        IntPredicate greaterThan15 = i -> i > 15;
        IntPredicate lessThan20 = i -> i < 20;
        System.out.println(greaterThan15.and(lessThan20).test(17));
    }

    private static void printEmployeesByAge(List<Employee> employees,
                                            String ageText,
                                            Predicate<Employee> ageCondition) {

        System.out.println(ageText);
        System.out.println("==================");
        for(Employee employee : employees) {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        }
    }
}
