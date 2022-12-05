package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    Collection<Employee> getAllEmployees();
    Employee addEmployee(EmployeeRequest employeeRequest);
    int getSalarySum();
    Employee getEmployeeSalaryMin();
    Employee getEmployeeSalaryMax();
    List<Employee> getAboveAverageSalary();
}
