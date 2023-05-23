package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
