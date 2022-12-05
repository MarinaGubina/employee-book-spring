/*
package com.skypro.employee.repository;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeesRepository{

    private final Map<Integer,Employee> employees = new HashMap<>();

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if(StringUtils.isBlank(employeeRequest.getFirstName()) ||
                !StringUtils.isAlpha(employeeRequest.getFirstName()) ||
                StringUtils.isBlank(employeeRequest.getLastName()) ||
                !StringUtils.isAlpha(employeeRequest.getLastName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else{
            Employee employee = new Employee(StringUtils.capitalize(employeeRequest.getFirstName()),
                StringUtils.capitalize(employeeRequest.getLastName()),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(),employee);
        return employee;
        }
    }

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }
}
*/
