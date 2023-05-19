package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.Employee;

import java.util.List;

public interface EmployeeRepository {
    public List<Employee> getAllEmployees();

    public Employee getEmployeesById(int id);
    public void addEmployees(Employee employee);
    public void editEmployees(Employee employee,int id);
    public void deleteEmploees(Employee employee,int id);
}
