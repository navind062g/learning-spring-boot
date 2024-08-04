package com.luv2code.cruddemo.dao;


import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using the constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer studentID) {
        Student myStudent = this.findById(studentID);
        System.out.println("Deleting student from database: "+myStudent);

        entityManager.remove(myStudent);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // create query
        TypedQuery<Student> theQuery = entityManager.
                createQuery("FROM Student WHERE lastName=:theData", Student.class);

        //set the query parameters
        theQuery.setParameter("theData", lastName);


        //return the results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void deleteAll() {
        int noOfStudents = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        System.out.println("Number of students deleted: "+noOfStudents);
    }
}




