package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{dep}/employees")
    public List<Employee> getEmployeesFromDepartment(@PathVariable("dep") int dep){
        return this.departmentService.getEmployeesFromDepartment(dep);
    }

    @GetMapping("/{dep}/salary/sum")
    public int getSalariesSumFromDepartment(@PathVariable("dep") int dep){
        return this.departmentService.getSumSalariesFromDepartment(dep);
    }

    @GetMapping("/{dep}/salary/max")
    public int getMaxSalaryFromDepartment(@PathVariable("dep") int dep){
        return this.departmentService.getMaxSalaryFromDepartment(dep);
    }

    @GetMapping("/{dep}/salary/min")
    public int getMinSalaryFromDepartment(@PathVariable("dep") int dep){
        return this.departmentService.getMinSalaryFromDepartment(dep);
    }

    @GetMapping("/employees")
    public Map<Integer,List<Employee>> getSortedEmployees(){
        return this.departmentService.getSortedEmployees();
    }
}
