package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO
{
    // define field for entity manager
    private EntityManager entityManager;


    // setup constructor injection for entity manager
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);

        // execute the query and get the result list
        List<Employee> employeeList = query.getResultList();

        // return the results
        return employeeList;
    }

    @Override
    public Employee findById(Integer id) {
        Employee theEmployee = this.entityManager.find(Employee.class, id);

        if(theEmployee == null) {
            throw new RuntimeException("Employee not found with ID - " + id);
        }

        return theEmployee;
    }

    // DAO does not handle the transactional, it is handled at the service layer
    @Override
    public Employee save(Employee theEmployee) {
        //if ID is 0, then it will be insert else it will be an update
       Employee dbEmployee = this.entityManager.merge(theEmployee);

       return dbEmployee;
    }

    // DAO does not handle the transactional, it is handled at the service layer
    @Override
    public void deleteById(int theId) {
        Employee deleteEmployee = this.entityManager.find(Employee.class, theId);

        entityManager.remove(deleteEmployee);
    }
}
