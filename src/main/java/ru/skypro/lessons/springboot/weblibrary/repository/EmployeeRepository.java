package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.skypro.lessons.springboot.weblibrary.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e")
    List<Employee> findAllEmployees();
    @Query(value = "SELECT * FROM employee WHERE id =:id",
    nativeQuery = true)
    List<Employee> findEmployeeById(@Param("id") int id);
    @Query("SELECT e FROM Employee e")
    List<Employee> getHighestSalary();
    @Query (value = "SELECT employee.id,employee.name,employee.salary,employee.position,position.position_name FROM position JOIN employee ON position.position_id=employee.position AND employee.position=:position",
    nativeQuery = true)
    List<Employee> allEmployeeFromPosition(@Param("position") int position);
    @Query (value = "SELECT employee.id,employee.name,employee.salary,employee.position,position.position_name FROM position JOIN employee ON position.position_id=employee.position AND employee.id=:id",
    nativeQuery = true)
    List<Employee> employeeFullInfo(@Param("id")int id);
}
