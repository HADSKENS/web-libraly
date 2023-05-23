package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.Employee;

import java.util.Optional;

public interface EmployeeService  {
    void addEmployee(Employee employee);
    Optional<Employee> getEmployeeById(int id);
    Iterable<Employee> getAll();
}