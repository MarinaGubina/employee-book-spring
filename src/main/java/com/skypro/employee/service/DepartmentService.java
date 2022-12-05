package com.skypro.employee.service;

import com.skypro.employee.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employee> getEmployeesFromDepartment(int dep);
    int getSumSalariesFromDepartment(int dep);
    int getMaxSalaryFromDepartment(int dep);
    int getMinSalaryFromDepartment(int dep);
    Map<Integer,List<Employee>> getSortedEmployees();
}
