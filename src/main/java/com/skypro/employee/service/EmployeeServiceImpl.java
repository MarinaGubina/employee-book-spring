package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.repository.EmployeesRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeesRepository employeesRepository;
    static final String MESSAGE_EXCEPTION = "Нет сотрудников.";

    public EmployeeServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public Employee addEmployee(EmployeeRequest employeeRequest){
        return employeesRepository.addEmployee(employeeRequest);
    }

    @Override
    public Collection<Employee> getAllEmployees(){
        return employeesRepository.getAllEmployees();
    }

    @Override
    public int getSalarySum(){
        return employeesRepository.getAllEmployees().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Employee getEmployeeSalaryMin(){
         return employeesRepository.getAllEmployees().stream()
                 .min((s1,s2)-> Integer.compare(s1.getSalary(),s2.getSalary()))
                 .orElseThrow(()-> new RuntimeException(MESSAGE_EXCEPTION));
    }

    @Override
    public Employee getEmployeeSalaryMax(){
        return employeesRepository.getAllEmployees().stream()
                .max((s1,s2)->Integer.compare(s1.getSalary(),s2.getSalary()))
                .orElseThrow(()-> new RuntimeException(MESSAGE_EXCEPTION));
    }

    @Override
    public List<Employee> getAboveAverageSalary(){
        double averageSalary = employeesRepository.getAllEmployees().stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElseThrow(()-> new RuntimeException(MESSAGE_EXCEPTION));

        return employeesRepository.getAllEmployees().stream()
                .filter(e -> e.getSalary() > averageSalary)
                .collect(Collectors.toList());
    }
}