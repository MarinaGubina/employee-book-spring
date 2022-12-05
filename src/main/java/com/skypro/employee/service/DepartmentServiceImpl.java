package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    // получение сотрудников из департамента
    @Override
    public List<Employee> getEmployeesFromDepartment(int dep){
        List<Employee> listEmployeesFromDepartment =
        employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .collect(Collectors.toList());
        return listEmployeesFromDepartment;
    }

    // сумма зарплат по департаменту
    @Override
    public int getSumSalariesFromDepartment(int dep){
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    // максимальная зарплата по департаменту
    @Override
    public int getMaxSalaryFromDepartment(int dep){
        return  employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(()-> new RuntimeException(" Нет сотрудников в отделе: " + dep));
    }

    // минимальная зарплата по департаменту
    @Override
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
    @Override
    public Map<Integer,List<Employee>> getSortedEmployees(){
        Map<Integer,List<Employee>> sortedEmployees =
                employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return sortedEmployees;
    }

}
