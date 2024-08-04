package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    public void save(Student theStudent);

    public Student findById(Integer id);

    public List<Student> findAll();

    public List<Student> findByLastName(String lastName);

    //updating the student
    public void update(Student theStudent);

    //deleting a student
    public void delete(Integer studentID);

    //deleting all the students
    public void deleteAll();

}
