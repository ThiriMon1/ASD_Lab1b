package edu.miu.cse;

import edu.miu.cse.domain.Employee;
import edu.miu.cse.domain.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
    List<Employee> employees = new ArrayList<Employee>();

    employees.add(new Employee(111L,"Daniel","Agar", LocalDate.parse("2018-01-17"),105945.50,new PensionPlan("EX1089",LocalDate.parse("2023-01-17"),100.00)));
    employees.add(new Employee(112L,"Benard","Shaw", LocalDate.parse("2019-04-03"),197750.00,null));
    employees.add(new Employee(113L,"Carly","Agar", LocalDate.parse("2014-05-16"),842000.75,new PensionPlan("SM2307",LocalDate.parse("2019-11-04"),1555.50)));
    employees.add(new Employee(114L,"Wesley","Schneider", LocalDate.parse("2019-10-02"),74500.00,null));

    printEmployees(employees);

    }
    private static void printEmployees(List<Employee> employees) {
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .collect(Collectors.toList());

        System.out.println("JSON :");
        String jsonOutput = sortedEmployees.stream()
                .map(employee -> String.format("  { \"firstName\": \"%s\",    \"lastName\": \"%s\",    \"employmentDate\": %s,    \"yearlySalary\": %.2f  }",
                         employee.getFirstName(), employee.getLastName(), employee.getEmploymentDate(), employee.getYearlySalary()))
                .collect(Collectors.joining(",\n", "[\n", "\n]"));
        System.out.println(jsonOutput);

        System.out.println("JSON :");
        String jsonOutput1 = sortedEmployees.stream()
                .map(employee -> String.format("  {\"planReferenceNumber\": \"%s\"," +
                                "\"firstName\": \"%s\",    " +
                                "\"lastName\": \"%s\",    \"employmentDate\": %s,    " +
                                "\"yearlySalary\": %.2f  \"enrollmentDate\": %s, " +
                                "\"monthlyContribution\": %.2f, }",
                       (employee.getPensionPlan()!=null)?employee.getPensionPlan().getPlanReferenceNumber():"",
                        employee.getFirstName(), employee.getLastName(),
                        employee.getEmploymentDate(), employee.getYearlySalary(),(employee.getPensionPlan()!=null)?employee.getPensionPlan().getEnrollmentDate():"",(employee.getPensionPlan()!=null)?employee.getPensionPlan().getMonthlyContribution():0))
                .collect(Collectors.joining(",\n", "[\n", "\n]"));
        System.out.println(jsonOutput1);

        List<Employee> upcomingEnrolls = employees.stream()
                .filter(employee -> employee.getPensionPlan() ==null)
                .filter(employee -> employee.getEmploymentDate().isBefore(LocalDate.now().minusYears(5).minusMonths(1)))
                .collect(Collectors.toList());

        List<Employee> sortedUpcomingEmployees = upcomingEnrolls.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .collect(Collectors.toList());

        System.out.println("JSON :");
        String jsonOutput2 = sortedUpcomingEmployees.stream()
                .map(employee -> String.format("  {\"planReferenceNumber\": \"%s\"," +
                                "\"firstName\": \"%s\",    " +
                                "\"lastName\": \"%s\",    \"employmentDate\": %s,    " +
                                "\"yearlySalary\": %.2f  \"enrollmentDate\": %s, " +
                                "\"monthlyContribution\": %.2f, }",
                        (employee.getPensionPlan()!=null)?employee.getPensionPlan().getPlanReferenceNumber():"",
                        employee.getFirstName(), employee.getLastName(),
                        employee.getEmploymentDate(), employee.getYearlySalary(),(employee.getPensionPlan()!=null)?employee.getPensionPlan().getEnrollmentDate():"",(employee.getPensionPlan()!=null)?employee.getPensionPlan().getMonthlyContribution():0))
                .collect(Collectors.joining(",\n", "[\n", "\n]"));
        System.out.println(jsonOutput2);

    }
}