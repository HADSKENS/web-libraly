package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeesById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public void addEmployees(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }
    @GetMapping("/get")
   public Iterable<Employee> getAll(){
        return employeeService.getAll();
    }

}