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
        a.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());
        return a.get(a.size()-1);
    }

    public Employee showSalaryMin() {
        List<Employee> a = employeeRepository.getAllEmployees();
        a.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());
        return a.get(0);
    }

    public List<Employee> showHighSalary() {
        List<Employee> a = employeeRepository.getAllEmployees();
        List<Employee> b = new ArrayList<>();
        int count=0;
        for (int i = 0; i < a.size(); i++) {
            count+=a.get(i).getSalary();
        }
        count=count/a.size();
        int finalCount = count;
        b=a.stream()
                .filter(i -> i.getSalary()> finalCount)
                .collect(Collectors.toList());
        return b;
    }
}
