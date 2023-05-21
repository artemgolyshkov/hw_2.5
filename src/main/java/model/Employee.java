package model;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Employee {
    @JsonProperty("firstName")
    private final String firstName;
    @JsonProperty("lastName")
    private final String lastName;
    @JsonProperty("salary")
    private int salary; // зарплата
    @JsonProperty("departmentId")
    private int departmentId; // отдел

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName, int salary, int departmentId) {
        this(firstName, lastName);
        this.salary = salary;
        this.departmentId = departmentId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getSalary() {
        return salary;
    }
    public int getDepartmentId() {
        return departmentId;
    }
    public void setSalary(final int salary) {
        this.salary = Math.max(salary, 0);
    }
    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
    }