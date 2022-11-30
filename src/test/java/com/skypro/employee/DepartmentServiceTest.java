package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.repository.EmployeesRepository;
import com.skypro.employee.service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeesRepository employeesRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private List<Employee> actualEmployees;

    @BeforeEach
    public void setUp(){
        Employee employee1 = new Employee("Ivan","Ivanov",2,22000);
        Employee employee2 = new Employee("Katya","Katina",1,33000);
        Employee employee3 = new Employee("Petr","Petrov",3,25000);
        Employee employee4 = new Employee("Asya","Asina",1,20000);
        actualEmployees = new ArrayList<>(List.of(employee1,employee2,employee3,employee4));

        when(employeesRepository.getAllEmployees()).thenReturn(actualEmployees);
    }

    @Test
    public void shouldReturnEmployeesFromDepartment(){
        int department = 1;
        final List<Employee> actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        final List<Employee> expected = departmentService.getEmployeesFromDepartment(department);
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnSumSalaryFromDepartment(){
        int department = 1;
        final int actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
        final int expected = departmentService.getSumSalariesFromDepartment(department);
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnMaxSalaryFromDepartment(){
        int department = 1;
        final int actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(()-> new RuntimeException(" Нет сотрудников в отделе: " + department));
        final int expected = departmentService.getMaxSalaryFromDepartment(department);
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnMinSalaryFromDepartment(){
        int department = 1;
        final int actual = actualEmployees.stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(()-> new RuntimeException(" Нет сотрудников в отделе: " + department));
        final int expected = departmentService.getMinSalaryFromDepartment(department);
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnSortedMapByDepartmentEmployees(){
        final Map<Integer,List<Employee>> actual =
                actualEmployees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));
        final Map<Integer,List<Employee>> expected = departmentService.getSortedEmployees();
        assertEquals(expected,actual);
    }
}
