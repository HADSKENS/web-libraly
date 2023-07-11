package ru.skypro.lessons.springboot.weblibrary.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    private static final Employee a = new Employee(1,"1",1,1);
    private final EmployeeRepository repositoryMock = mock(EmployeeRepository.class);
    @InjectMocks
    private EmployeeServiceImpl out;
    List<Employee> b = (List<Employee>) repositoryMock.findAll();
    List<Employee> c = repositoryMock.allEmployeeFromPosition(1);
    List<EmployeeDTO> d = repositoryMock.employeeFullInfo(1).stream()
            .map(EmployeeDTO::fromEmployee)
            .collect(Collectors.toList());
    private static final Employee e = new Employee(2,"2",2,2);
    @BeforeEach
    public void init() {
        out = new EmployeeServiceImpl(repositoryMock);
    }

    @Test
    public void getAllTest(){
        when(repositoryMock.findAll())
                .thenReturn(b);

        Assertions.assertIterableEquals(out.getAll(),b);
    }
    @Test
    public void getEmployeeByIdTest(){
        when(repositoryMock.findEmployeeById(a.getId()))
                .thenReturn((List<Employee>) a);

        Assertions.assertEquals(out.getEmployeeById(a.getId()),a);
    }
    @Test
    public void allEmployeeFromPositionTest(){
        when(repositoryMock.allEmployeeFromPosition(1))
                .thenReturn(b);

        Assertions.assertEquals(out.allEmployeeFromPosition(1),c);
    }
    @Test
    public void employeeFullInfoTest(){
        when(repositoryMock.employeeFullInfo(1))
                .thenReturn((List<Employee>) d.stream().map(EmployeeDTO::toEmployee));
        Assertions.assertEquals(out.employeeFullInfo(a.getId()), d);
    }
    @Test
    public void addEmployeeTest(){
        out.addEmployee(a);
        when(repositoryMock.findEmployeeById(a.getId()))
                .thenReturn((List<Employee>) a);
        Assertions.assertEquals(out.getEmployeeById(a.getId()),a);
        out.deleteEmployeeById(a.getId());
    }
    @Test
    public void deleteEmployeeTest(){
        out.addEmployee(a);
        when(repositoryMock.findEmployeeById(a.getId()))
                .thenReturn(null);
        out.deleteEmployeeById(a.getId());
        Assertions.assertNull(out.getEmployeeById(a.getId()));

    }
}
