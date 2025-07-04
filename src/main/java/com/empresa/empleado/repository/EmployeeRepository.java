package com.empresa.empleado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.empleado.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Case-insensitive partial search by name
    List<Employee> findByFirstNameContainingIgnoreCase(String name);

}
