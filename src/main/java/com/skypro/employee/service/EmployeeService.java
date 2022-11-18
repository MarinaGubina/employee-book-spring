package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees(){
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest){
        if(StringUtils.isBlank(employeeRequest.getFirstName()) ||
                !StringUtils.isAlpha(employeeRequest.getFirstName()) ||
                StringUtils.isBlank(employeeRequest.getLastName()) ||
                !StringUtils.isAlpha(employeeRequest.getLastName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Employee employee = new Employee(StringUtils.capitalize(employeeRequest.getFirstName()),
                StringUtils.capitalize(employeeRequest.getLastName()),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(),employee);
        return employee;
    }

    public int getSalarySum(){
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getEmployeeSalaryMin(){
         return employees.values().stream()
                 .min((s1,s2)-> Integer.compare(s1.getSalary(),s2.getSalary()))
                 .orElse(null);
    }

    public Employee getEmployeeSalaryMax(){
        return employees.values().stream()
                .max((s1,s2)->Integer.compare(s1.getSalary(),s2.getSalary()))
                .orElse(null);
    }

    public List<Employee> getAboveAverageSalary(){
        double averageSalary = employees.values().stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElseThrow();

        return employees.values().stream()
                .filter(e -> e.getSalary() > averageSalary)
                .collect(Collectors.toList());
    }

}

