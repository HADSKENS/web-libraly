package ru.skypro.lessons.springboot.weblibrary.service;

import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public String showSumSalary(){
        int count=0;
        List<Employee> a = employeeRepository.getAllEmployees();
        for (int i = 0; i < a.size(); i++) {
            count+=a.get(i).getSalary();
        }
        return "Сумма зарплат всех сотрудников: "+ count;
    }
    public Employee showSalaryMax(){
        List<Employee> a = employeeRepository.getAllEmployees();
        Employee b=a.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .get();
        return b;
    }

    public Employee showSalaryMin() {
        List<Employee> a = employeeRepository.getAllEmployees();
        Employee b=a.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .get();
        return b;
    }

    public List<Employee> showHighSalary(int salary) {
        List<Employee> a = employeeRepository.getAllEmployees();
        List<Employee> b = new ArrayList<>();
        b=a.stream()
                .filter(i -> i.getSalary()> salary)
                .collect(Collectors.toList());
        return b;
    }

    public void addEmployees(Employee employee) {
        employeeRepository.addEmployees(employee);
    }
    public void editEmployees(Employee employee,int id){
       employeeRepository.editEmployees(employee,id);
    }
    public void deleteEmployees(Employee employee,int id){
        employeeRepository.deleteEmploees(employee,id);
    }
}
