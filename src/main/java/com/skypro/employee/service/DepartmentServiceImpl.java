package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.repository.EmployeesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final EmployeesRepository employeesRepository;

    public DepartmentServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    // получение сотрудников из департамента
    @Override
    public List<Employee> getEmployeesFromDepartment(int dep){
        List<Employee> listEmployeesFromDepartment =
        employeesRepository.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .collect(Collectors.toList());
        return listEmployeesFromDepartment;
    }

    // сумма зарплат по департаменту
    @Override
    public int getSumSalariesFromDepartment(int dep){
        return employeesRepository.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    // максимальная зарплата по департаменту
    @Override
    public int getMaxSalaryFromDepartment(int dep){
        return  employeesRepository.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(()-> new RuntimeException(" Нет сотрудников в отделе: " + dep));
    }

    // минимальная зарплата по департаменту
    @Override
    public int getMinSalaryFromDepartment(int dep){
        return employeesRepository.getAllEmployees().stream()
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
                employeesRepository.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return sortedEmployees;
    }

}
