package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import com.skypro.employee.service.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private List<Employee> employees;

    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeServiceImpl();
        Stream.of (
        new EmployeeRequest("Ivan", "Ivanov", 2, 22000),
        new EmployeeRequest("Katya", "Katina", 1, 33000),
        new EmployeeRequest("Petr", "Petrov", 3, 25000))
                .forEach(employeeService::addEmployee);
    }

    @Test
    public void shouldAddEmployees() {
        EmployeeRequest request = new EmployeeRequest("Masha", "Petrova", 5, 10000);
        Employee result = employeeService.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDepartment(), result.getDepartment());
        assertEquals(request.getSalary(), result.getSalary());
        Assertions.assertThat(employeeService.getAllEmployees()).contains(result);
    }

    @Test
    public void shouldGetSalarySumAllEmployees() {
        assertEquals(80_000, employeeService.getSalarySum());
    }

    @Test
    public void shouldGetSalaryMinAmongAllEmployees() {
        assertEquals(22_000, employeeService.getEmployeeSalaryMin().getSalary());
    }

    @Test
    public void shouldGetSalaryMaxAmongAllEmployees() {
        assertEquals(33_000, employeeService.getEmployeeSalaryMax().getSalary());
    }

    @Test
    public void shouldReturnAllEmployeesAboveAverageSalary(){
        final double averageSalary = employeeService.getAllEmployees().stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElseThrow();

        final List<Employee> actual = employeeService.getAllEmployees().stream()
                .filter(e -> e.getSalary() > averageSalary)
                .collect(Collectors.toList());
        final List<Employee> expected = employeeService.getAboveAverageSalary();
        assertEquals(expected,actual);
    }
}