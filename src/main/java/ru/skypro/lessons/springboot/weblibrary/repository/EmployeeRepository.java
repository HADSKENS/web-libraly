package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.Employee;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    @Query("SELECT e FROM Employee e")
    List<Employee> findAllEmployees();
    Employee findEmployeeById(int id);
    @Query (value = "SELECT employee.id,employee.name,employee.salary,employee.position,position.position_name FROM position JOIN employee ON position.position_id=employee.position AND employee.position=:position",
    nativeQuery = true)
    List<Employee> allEmployeeFromPosition(@Param("position") int position);
    @Query (value = "SELECT employee.id,employee.name,employee.salary,employee.position,position.position_name FROM position JOIN employee ON position.position_id=employee.position AND employee.id=:id",
    nativeQuery = true)
    List<Employee> employeeFullInfo(@Param("id")int id);
}
