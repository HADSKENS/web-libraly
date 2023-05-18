package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    String showSumSalary();
    Employee showSalaryMax();
    Employee showSalaryMin();
    List<Employee> showHighSalary(int salary);
    void addEmployees(Employee employee);
    void editEmployees(Employee employee,int id);
    void deleteEmployees(Employee employee,int id);
}