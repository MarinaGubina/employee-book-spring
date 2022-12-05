package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentServiceImpl departmentServiceImpl;

    public DepartmentController(DepartmentServiceImpl departmentServiceImpl) {
        this.departmentServiceImpl = departmentServiceImpl;
    }

    @GetMapping("/{dep}/employees")
    public List<Employee> getEmployeesFromDepartment(@PathVariable("dep") int dep){
        return this.departmentServiceImpl.getEmployeesFromDepartment(dep);
    }

    @GetMapping("/{dep}/salary/sum")
    public int getSalariesSumFromDepartment(@PathVariable("dep") int dep){
        return this.departmentServiceImpl.getSumSalariesFromDepartment(dep);
    }

    @GetMapping("/{dep}/salary/max")
    public int getMaxSalaryFromDepartment(@PathVariable("dep") int dep){
        return this.departmentServiceImpl.getMaxSalaryFromDepartment(dep);
    }

    @GetMapping("/{dep}/salary/min")
    public int getMinSalaryFromDepartment(@PathVariable("dep") int dep){
        return this.departmentServiceImpl.getMinSalaryFromDepartment(dep);
    }

    @GetMapping("/employees")
    public Map<Integer,List<Employee>> getSortedEmployees(){
        return this.departmentServiceImpl.getSortedEmployees();
    }
}
