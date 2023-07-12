package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<EmployeeDTO> getEmployeeById(int id) {
        return employeeRepository.findEmployeeById(id).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> getHighestSalary() {
        return employeeRepository.getHighestSalary().stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> allEmployeeFromPosition(int position) {
        return employeeRepository.allEmployeeFromPosition(position).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> employeeFullInfo(int id) {
        return employeeRepository.employeeFullInfo(id).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> pageEmployee(int page) {
        List <EmployeeDTO> a =employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
        List<EmployeeDTO> back = new ArrayList<>();
        if (page<0){
            return null;
        }
        else{
            page=page*10+9;
            if(a.size()<page){
                for (int i = page-9; i < a.size(); i++) {
                    back.add(a.get(i));
                }
            }
            else {
                for (int i = page - 9; i < page+1; i++) {
                    back.add(a.get(i));
                }
            }
            return back;
        }
    }
}
