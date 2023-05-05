package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public List<Employee> showCounter() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/salary/sum")
    public String showSumSalary(){
        return employeeService.showSumSalary();
    }
    @GetMapping("/salary/min")
    public String showMinSalary(){
        List<Employee> a = employeeService.getAllEmployees();
        int salary=a.get(0).getSalary();
        for (int i = 0; i < a.size(); i++) {
            if(salary>a.get(i).getSalary()){
                salary=a.get(i).getSalary();
            }
        }
        return "Минимальная зарплата среди сотрудников: "+salary;
    }
    @GetMapping("/salary/max")
    public String showMaxSalary(){
        return employeeService.showSalaryMax();
    }
    @GetMapping("/high-salary")
    public String showHighSalary(){
        return employeeService.showHighSalary();
    }
}