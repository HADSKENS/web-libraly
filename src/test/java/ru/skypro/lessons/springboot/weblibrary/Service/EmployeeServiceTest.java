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

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    private static final Employee a = new Employee(1,"1",1,1);
    private final EmployeeRepository repositoryMock = mock(EmployeeRepository.class);
    @InjectMocks
    private EmployeeServiceImpl out;
    List<Employee> b = (List<Employee>) repositoryMock.findAll();
    List<Employee> c = repositoryMock.allEmployeeFromPosition(1);
    List<Employee> d = repositoryMock.employeeFullInfo(1);
    @BeforeEach
    public void init() {
        out = new EmployeeServiceImpl(repositoryMock);
    }

    @Test
    public void getAllTest(){
        when(repositoryMock.findAll())
                .thenReturn(b);

        assertIterableEquals(b, out.getAll());
    }
    @Test
    public void getEmployeeByIdTest(){
        when(repositoryMock.findEmployeeById(a.getId()))
                .thenReturn(a);
    }
    @Test
    public void allEmployeeFromPositionTest(){
        when(repositoryMock.allEmployeeFromPosition(1))
                .thenReturn(c);

        assertIterableEquals(c, out.allEmployeeFromPosition(1));
    }
    @Test
    public void employeeFullInfoTest(){
        when(repositoryMock.employeeFullInfo(1))
                .thenReturn(d);

        assertIterableEquals(d, out.employeeFullInfo(1));
    }
    @Test
    public void addEmployeeTest(){
        repositoryMock.save(a);
        when(repositoryMock.findEmployeeById(a.getId()))
                .thenReturn(a);
        repositoryMock.deleteById(a.getId());
    }
    @Test
    public void deleteEmployeeTest(){
        repositoryMock.save(a);
        when(repositoryMock.findEmployeeById(a.getId()))
                .thenReturn(a);
        repositoryMock.deleteById(a.getId());
        when(repositoryMock.findEmployeeById(a.getId()))
                .thenReturn(null);
    }
}
