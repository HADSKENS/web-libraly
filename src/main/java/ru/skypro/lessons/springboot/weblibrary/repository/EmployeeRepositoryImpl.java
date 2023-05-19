package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.Employee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private List<Employee> employeeList= new ArrayList<>();
    @Override
    public Employee getEmployeesById(int id) {
        Employee a = null;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId()==id){
                a = employeeList.get(i);
            }
        }
        return a;
    }
    public List<Employee> getAllEmployees() {
    return employeeList;
    }


    public void addEmployees(Employee employee){
        employeeList.add(employee);
     }
     public void editEmployees(Employee employee,int id){
         for (int i = 0; i < employeeList.size(); i++) {
             if (employeeList.get(i).getId()==id){
                 employeeList.get(i).setName(employee.getName());
                 employeeList.get(i).setSalary(employee.getSalary());
             }
         }
     }
     public void deleteEmploees(Employee employee,int id){
         for (int i = 0; i < employeeList.size(); i++) {
             if (employeeList.get(i).getId()==id){
                 employeeList.remove(i);
             }
         }
     }
}