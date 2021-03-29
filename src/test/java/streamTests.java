import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class streamTests {


    List<Employee> employees = new ArrayList<>();

    @Before
    public void setUp() {
        employees.add(new Employee("Jan", "Kowalski", 29, Arrays.asList("Java", "Python")));
        employees.add(new Employee("Janina", "Udemy", 25, Arrays.asList("Java Script", "HTML")));
        employees.add(new Employee("Janusz", "Amebowski", 39, Arrays.asList("PHP", "Ruby")));
        employees.add(new Employee("Jarek", "Kozera", 50, Arrays.asList("Java", "Scala")));
        employees.add(new Employee("Kajetan", "Masłowski", 45, Arrays.asList("C", "C++", "Python")));
    }

    @Test
    public void testPrint() {
        employees.forEach(System.out::println);
    }

    @Test
    public void mapOperation() {
        employees.stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .forEach(System.out::println);
    }

    @Test
    public void flatMapOperation() {
        List<List<String>> allSkillsList = employees.stream()
                .map(Employee::getSkills)
                .collect(Collectors.toList());

        System.out.println(allSkillsList);
        List<String> flatListSkills = employees.stream()
                .map(Employee::getSkills)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(flatListSkills);
    }

    @Test
    public void filterOperation() {
        employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("J"))
                .forEach(System.out::println);
    }

    @Test
    public void sortOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .forEach(System.out::println);
    }

    @Test
    public void limitOps() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void skipOps() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void countOps() {
        long employeesNumber = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .count();

        System.out.println(employeesNumber);
    }

    @Test
    public void minMaxOps() {
        Employee youngestEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getAge))
                .get();
        System.out.println(youngestEmployee);

        Employee theOldestEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getAge))
                .get();
        System.out.println(theOldestEmployee);
    }
}
