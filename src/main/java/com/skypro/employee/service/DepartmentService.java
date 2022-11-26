package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // получение сотрудников из департамента
    public List<Employee> getEmployeesFromDepartment(int dep){
        List<Employee> listEmployeesFromDepartment = new LinkedList<>();
        employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .collect(Collectors.toCollection(()->listEmployeesFromDepartment));
        return listEmployeesFromDepartment;
    }

    // сумма зарплат по департаменту
    public int getSumSalariesFromDepartment(int dep){
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    // максимальная зарплата по департаменту
    public int getMaxSalaryFromDepartment(int dep){
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(()-> new RuntimeException(" Нет сотрудников в отделе: " + dep));
    }

    // минимальная зарплата по департаменту
    public int getMinSalaryFromDepartment(int dep){
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(()-> new RuntimeException(" Нет сотрудников в отделе: " + dep));
    }

    // возвращает сотрудников, сгруппированых по отделам
    // в виде Map<Integer,List<Employees>>,
    // где ключ — это номер отдела, а значение — список сотрудников данного отдела.
    public Map<Integer,List<Employee>> getSortedEmployees(){
        Map<Integer,List<Employee>> sortedEmployees =
                employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return sortedEmployees;
    }

}
