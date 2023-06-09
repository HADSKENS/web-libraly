package ru.skypro.lessons.springboot.weblibrary.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl {

    private final EmployeeRepository employeeRepository;
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public void addEmployee(Employee employee) {
        logger.info("Was invoked method for create employee"+employee);
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(int id) {
        logger.error("There is no employee with id"+id);
        return employeeRepository.findEmployeeById(id);
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    public void deleteEmployeeById(int id) {
        logger.error("There is no employee with id"+id);
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> getHighestSalary() {
        return employeeRepository.findAllEmployees().stream()
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
        logger.error("There is no employee with id"+id);
        return employeeRepository.employeeFullInfo(id).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> pageEmployee(int page) {
        logger.error("There is no employee with id"+page);
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

    public void uploadFileEmployees(MultipartFile file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file.getName());
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<EmployeeDTO> a = null;
        a.add((EmployeeDTO) objectInputStream.readObject());
        for (int i = 0; i < a.size(); i++) {
            Employee b = a.get(i).toEmployee();
            employeeRepository.save(b);
        }
    }
}
