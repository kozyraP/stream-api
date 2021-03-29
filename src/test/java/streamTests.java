import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class streamTests {


    List<Employee> employees = new ArrayList<>();

    @Before
    public void setUp() {
        employees.add(new Employee("Jan", "Kowalski", 29, Arrays.asList("Java", "Python")));
        employees.add(new Employee("Janina", "Udemy", 25, Arrays.asList("Java Script", "HTML")));
        employees.add(new Employee("Jan", "Kowalski", 39, Arrays.asList("PHP", "Ruby")));
        employees.add(new Employee("Jan", "Kowalski", 50, Arrays.asList("Java", "Scala")));
        employees.add(new Employee("Jan", "Kowalski", 45, Arrays.asList("C", "C++", "Python")));
    }

    @Test
    public void testPrint(){
        employees.forEach(System.out::println);
    }

    @Test
    public void mapOperation(){
        employees.stream()
                .map(employee -> employee.getFirstName())
                .forEach(System.out::println);
    }
}