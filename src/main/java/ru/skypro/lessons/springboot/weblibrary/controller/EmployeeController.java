package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;
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
    public List<EmployeeDTO> getEmployeesById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public void addEmployees(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }
    @GetMapping("/get")
   public List<EmployeeDTO> getAll(){
        return employeeService.getAll();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeByid(@PathVariable int id){
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

    }

}