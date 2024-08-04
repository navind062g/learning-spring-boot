package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		studentDAO.deleteAll();
	}

	private void deleteStudent(StudentDAO studentDAO) {

		// retrieve the student using the student id
		int myStudentId = 1;
		System.out.println("Deleting the student with ID : "+myStudentId);

		// delete the student
		studentDAO.delete(myStudentId);

	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve the student using the student id
		System.out.println("Retrieving the student with id as 1.");
		Student tempStudent = studentDAO.findById(1);

		//update the student firstName
		System.out.println("Updating the first Name.");
		tempStudent.setFirstName("Scobby");

		System.out.println("Saving the student into the database.");
		//call the update student method on studentDAO
		studentDAO.update(tempStudent);

		//display the updated student
		System.out.println(tempStudent);

	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		// retrieve the students using last name
		List<Student> students = studentDAO.findByLastName("Duck");

		// print the students using stream
		students.stream().forEach(System.out::println);
	}

	private void queryForStudents(StudentDAO studentDAO) {

		List<Student> studentList = studentDAO.findAll();

		for(Student tempStudent: studentList) {
			System.out.println(tempStudent);
		}

	}

	private void readStudent(StudentDAO studentDAO) {

		System.out.println("Creating a new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

		//save the object to the database
		studentDAO.save(tempStudent);

		//print the student id for the newly created student object
		System.out.println("Student ID of newly created student is " + tempStudent.getId());

		//retreieve the student id
		Integer studentId = tempStudent.getId();

		//retrieve the student object using the student id
		System.out.println("Retrieving the student from the database: "+ studentId);
		Student retrievedStudent = studentDAO.findById(studentId);

		//print the student if found
		if(retrievedStudent != null) {
			System.out.println(retrievedStudent);
		}

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("John", "Doe", "paul@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		// save the student object
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		// display id of the save student
		System.out.println("Saved Students. Generated ID of the student1: "+ tempStudent1.getId());
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating the new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the save student
		System.out.println("Saved Student. Generated ID of the student: "+ tempStudent.getId());
	}

}
