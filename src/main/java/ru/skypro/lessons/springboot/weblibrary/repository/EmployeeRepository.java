package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.Employee;

import java.util.List;

public interface EmployeeRepository {

    public List<Employee> getAllEmployees();
}
