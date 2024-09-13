package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


//extends the JpaRepository using Employee and Integer as Entity Type and Return Value
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
}
