package service;
import org.springframework.stereotype.Service;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;
import pro.sky.employeebook.exception.EmployeeStorageIsFullException;
import pro.sky.employeebook.model.Employee;

import java.util.*;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees;
    private static final int LIMIT = 10;
    private final ValidatorService validatorService;
    public EmployeeServiceImpl(final ValidatorService validatorService) {
        this.validatorService = validatorService;
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName,
                                String lastName,
                                int salary,
                                int departmentId
    ) {
        Employee employee = new Employee(
                validatorService.validateName(firstName),
                validatorService.validateSurname(lastName),
                salary,
                departmentId
        );
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Employee " + firstName + " " + lastName + " Has Already Been Added");
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employees
                .stream()
                .filter(emp -> Objects.equals(firstName, emp.getFirstName()) && Objects.equals(lastName, emp.getLastName()))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + firstName + " " + lastName + " Not Found"));
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        final Optional<Employee> employee = employees
                .stream()
                .filter(emp -> Objects.equals(firstName, emp.getFirstName()) && Objects.equals(lastName, emp.getLastName()))
                .findFirst();
        return employee.orElseThrow(() -> new EmployeeNotFoundException("Employee " + firstName + " " + lastName + " Not Found"));
    }
    public Collection<Employee> printEmployees() {
        return Collections.unmodifiableList(employees);
    }
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee setSalary(final String firstName, final String lastName, final int salary) {
        final Optional<Employee> employee = employees
                .stream()
                .filter(emp -> Objects.equals(firstName, emp.getFirstName()) && Objects.equals(lastName, emp.getLastName()))
                .findFirst();
        employee.ifPresent(emp -> emp.setSalary(salary));
        return employee.orElseThrow(() -> new EmployeeNotFoundException("Employee " + firstName + " " + lastName + " Not Found"));
    }
    @Override
    public int calcTotalMonthlySalary() {
        return employees
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }
    @Override
    public Employee getEmployeeWithMaxSalary() {
        return getEmployeeWithMaxSalary(employees);
    }
    private Employee getEmployeeWithMaxSalary(List<Employee> employees) {
        final Optional<Employee> employee = employees
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary));
        return employee.orElseThrow(() -> new RuntimeException("Employee with max salary not found"));
    }
    @Override
    public Employee getEmployeeWithMinSalary() {
        return getEmployeeWithMinSalary(employees);
    }
    private Employee getEmployeeWithMinSalary(List<Employee> employees) {
        final Optional<Employee> employee = employees
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary));
        return employee.orElseThrow(() -> new RuntimeException("Employee with min salary not found"));
    }
    }
