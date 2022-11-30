package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.repository.EmployeesRepository;
import com.skypro.employee.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeesRepository employeesRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private List<Employee> actualEmployees;

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Ivan", "Ivanov", 2, 22000);
        Employee employee2 = new Employee("Katya", "Katina", 1, 33000);
        Employee employee3 = new Employee("Petr", "Petrov", 3, 25000);
        Employee employee4 = new Employee("Asya", "Asina", 1, 20000);
        actualEmployees = new ArrayList<>(List.of(employee1, employee2, employee3, employee4));

        when(employeesRepository.getAllEmployees()).thenReturn(actualEmployees);
    }

    @Test
    public void shouldGetListAllEmployees() {
        Collection<Employee> expected = employeeService.getAllEmployees();
        assertEquals(expected, actualEmployees);
    }

    @Test
    public void shouldAddEmployees() {
        EmployeeRequest request = new EmployeeRequest("Petr", "Petrov", 1, 25000);
        Employee result = employeeService.addEmployee(request);
        assertThat(employeeService.getAllEmployees()).contains(result);
    }

    @Test
    public void shouldGetSalarySumAllEmployees() {
        final int expected  = actualEmployees.stream()
                .mapToInt(Employee::getSalary)
                .sum();
        final int actual = employeeService.getSalarySum();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSalaryMinAmongAllEmployees() {
        final int actual = employeeService.getEmployeeSalaryMin().getSalary();
        assertEquals(20000, actual);
    }

    @Test
    public void shouldGetSalaryMaxAmongAllEmployees() {
        final int actual = employeeService.getEmployeeSalaryMax().getSalary();
        assertEquals(33000, actual);
    }

    @Test
    public void shouldReturnAllEmployeesAboveAverageSalary(){
        final double averageSalary = actualEmployees.stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElseThrow();

        final List<Employee> actual = actualEmployees.stream()
                .filter(e -> e.getSalary() > averageSalary)
                .collect(Collectors.toList());
        final List<Employee> expected = employeeService.getAboveAverageSalary();
        assertEquals(expected,actual);
    }
}