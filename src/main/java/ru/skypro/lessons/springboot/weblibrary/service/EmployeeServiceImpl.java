package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

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
    public String showSalaryMax(){
        List<Employee> a = employeeRepository.getAllEmployees();
        List <Integer> b = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            b.add(a.get(i).getSalary());
        }
        b.stream().sorted().collect(Collectors.toList());
        return "Максимальная зарплата серди сотрудников: "+b.get(b.size()-1);
    }

    public String showSalaryMin() {
        List<Employee> a = employeeRepository.getAllEmployees();
        List <Integer> b = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            b.add(a.get(i).getSalary());
        }
        b.stream().sorted().collect(Collectors.toList());
        return "Минимальная зарплата серди сотрудников: "+b.get(0);
    }

    public String showHighSalary() {
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
        return "Сотрудники, зарплата которых больше средней: "+b;
    }
}
