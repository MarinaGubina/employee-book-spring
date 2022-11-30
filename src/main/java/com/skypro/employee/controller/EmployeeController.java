package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees(){
        return this.employeeServiceImpl.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return this.employeeServiceImpl.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum(){
        return this.employeeServiceImpl.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public Employee getEmployeeSalaryMin(){
        return this.employeeServiceImpl.getEmployeeSalaryMin();
    }

    @GetMapping("/employees/salary/max")
    public Employee getEmployeeSalaryMax(){
        return this.employeeServiceImpl.getEmployeeSalaryMax();
    }

    @GetMapping("/employees/high-salary")
    public List<Employee> getAboveAverageSalary(){
        return this.employeeServiceImpl.getAboveAverageSalary();
    }

}
