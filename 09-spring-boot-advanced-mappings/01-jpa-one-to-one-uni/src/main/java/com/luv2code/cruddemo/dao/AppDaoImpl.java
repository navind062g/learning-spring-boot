package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDaoImpl implements  AppDao{

    //define field for the entity manager
    private EntityManager entityManager;

    //inject the entity manager using constructor injection
    @Autowired
    public AppDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }


    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        this.entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return this.entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the instructor
        Instructor instructor = this.entityManager.find(Instructor.class, theId);

        //delete the instructor
        this.entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return this.entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = this.entityManager.find(InstructorDetail.class, theId);

        // removing the reference to the associated Instructor Detail in Instructor Class

        // removing the bidirectional link between Instructor and Instructor Detail
        instructorDetail.getInstructor().setInstructorDetail(null);

        this.entityManager.remove(instructorDetail);
    }
}
