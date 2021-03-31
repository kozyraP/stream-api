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

    @Test
    public void findAny() {
        Employee emp = employees.stream()
                .filter(employee -> employee.getSkills().contains("Java"))
                .findAny() //.findFirst - another option to find
                .orElse(null);
        System.out.println(emp);
    }

    @Test
    public void matchStream() {
        System.out.println(employees.stream()
                .allMatch(employee -> employee.getAge() > 18)
        );

        System.out.println(employees.stream()
                .anyMatch(employee -> employee.getSkills().contains("Java"))
        );

        System.out.println(employees.stream()
                .noneMatch(employee -> employee.getLastName().startsWith("K"))
        );
    }

    @Test
    public void countAllSkills() {
        System.out.println(employees.stream()
                .map(Employee::getSkills)
                .flatMap(Collection::stream)
                .distinct()
                .count());
    }

    @Test
    public void reduceOpsStream() {
        Integer sumOfAllAges = employees.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum)
                .get();
        System.out.println(sumOfAllAges);

        Integer sumOfAllAges2 = employees.stream()
                .map(Employee::getAge)
                .reduce(1000, Integer::sum);
        System.out.println(sumOfAllAges2);

        Integer sumOfAllAges3 = employees.stream()
                .reduce(0, (age, emp) -> age + emp.getAge(), Integer::sum);
        System.out.println(sumOfAllAges3);

        System.out.println(employees.stream()
                .map(emp -> emp.getFirstName() + " " + emp.getLastName())
                .reduce((fullName, fullName2) -> fullName + " | " + fullName2)
                .orElse("")
        );
    }

    @Test
    public void takeWhileOps(){
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .takeWhile(emp -> emp.getAge() < 30)
                .forEach(System.out::println);

        //in this case using filter is also appropriate, but in large data set it will be
        //better because this way quit processing when reach to takeWhile predicate.
    }
}
