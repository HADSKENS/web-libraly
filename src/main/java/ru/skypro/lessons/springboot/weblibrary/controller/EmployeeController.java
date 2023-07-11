package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeServiceImpl;

import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/{id}")
    public List<EmployeeDTO> getEmployeesById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/add")
    public void addEmployees(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }
    @GetMapping("/get")
   public List<EmployeeDTO> getAll(){
        return employeeService.getAll();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable int id){
        employeeService.deleteEmployeeById(id);
    }
    @GetMapping("/withHighestSalary")
    public EmployeeDTO getHighestSalary(){
        return employeeService.getHighestSalary().get(employeeService.getHighestSalary().size()-1);
    }
    @GetMapping
    public List<EmployeeDTO> allEmployeeFromPosition(@RequestParam int position){
        return employeeService.allEmployeeFromPosition(position);
    }
    @GetMapping("/{id}/fullinfo")
    public List<EmployeeDTO> employeeFullInfo(@PathVariable int id){
        return employeeService.employeeFullInfo(id);
    }
    @GetMapping("/page")
    public List<EmployeeDTO> pageEmployee(@RequestParam int page){
        return employeeService.pageEmployee(page);
    }

}