package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;

import java.io.IOException;
import java.util.List;

public interface EmployeeService  {
    void addEmployee(Employee employee);
    List<EmployeeDTO> getEmployeeById(int id);
    List<EmployeeDTO> getAll();
    void deleteEmployeeById(int id);
    List<EmployeeDTO> getHighestSalary();
    List<EmployeeDTO> allEmployeeFromPosition(int position);
    List<EmployeeDTO> employeeFullInfo(int id);
    List<EmployeeDTO> pageEmployee(int page);
    void uploadFileEmployees(MultipartFile file) throws IOException, ClassNotFoundException;
}