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
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeeById(int id) {
        return employeeRepository.findEmployeeById(id).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getHighestSalary() {
        return employeeRepository.getHighestSalary().stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> allEmployeeFromPosition(int position) {
        return employeeRepository.allEmployeeFromPosition(position).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> employeeFullInfo(int id) {
        return employeeRepository.employeeFullInfo(id).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> pageEmployee(int page) {
        List <EmployeeDTO> a =employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
        List<EmployeeDTO> back = new ArrayList<>();
        if (page<0){
            return null;
        } if (page==0) {
            page=10;
            for (int i = 0; i < page; i++) {
                back.add(i,a.get(i));
            }
            return back;
        }
        else{
            page=page*10;
            for (int i = page-10; i < page; i++) {
                back.add(i,a.get(i));
            }
            return back;
        }
    }
}
