package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
}