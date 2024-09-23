package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDao;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Autowired
	public CommandLineRunner commandLineRunner(@Qualifier("appDaoImpl") AppDao appDao) {
		return runner -> {
			//createCourseAndReviews(appDao);
			findCourseAndReviews(appDao);
		};
	}

	private void findCourseAndReviews(AppDao appDao) {
		int theId = 14;

		System.out.println("Fetching the Course");
		Course tempCourse = appDao.findCourseAndReviewsByCourseId(theId);

		System.out.println("Course: "+tempCourse);
		System.out.println("And Reviews are: "+ tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDao appDao) {
		// create a course
		Course tempCourse = new Course("Pacman - How to Score One Million Points");

		// add some reviews
		tempCourse.add(new Review("Great Course!!! Loved it!"));
		tempCourse.add(new Review("Cool Course!!! Job Well Done!"));
		tempCourse.add(new Review("What a Dump Course!! you are an idiot!!"));

		// save the course
		System.out.println("Saving the Course and its Reviews");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDao.save(tempCourse);
		System.out.println("Done!!!");
	}

	private void deleteCourse(AppDao appDao) {
		int theId = 11;

		System.out.println("Deleting the course with ID: "+theId);

		appDao.deleteCourseById(theId);

		System.out.println("Done!!!");
	}

	private void updateCourse(AppDao appDao) {
		// set the course id
		int theId = 11;

		// retrieve the course using appDao
		Course tempCourse = appDao.findCourseById(theId);

		// update the course title
		tempCourse.setTitle("The Pinball Master Environment");

		// perform update
		appDao.update(tempCourse);

	}

	private void updateInstructor(AppDao appDao) {
		int theId = 1;

		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("Updating the instructor with id: "+theId);
		tempInstructor.setLastName("TESTER");

		appDao.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
		int theId = 1;

		System.out.println("Finding the Instructor with ID: "+theId);
		Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);

		System.out.println("Temp Instructor: " + tempInstructor);
		System.out.println("Associated Courses for that Instructor " + tempInstructor.getCourses());
	}

	private void findInstructorWithCourses(AppDao appDao) {
		int theId = 1;

		System.out.println("Finding the Instructor with ID: " + theId);

		Instructor instructor = appDao.findInstructorById(theId);

		System.out.println("Temp Instructor is " + instructor);
		System.out.println("Associated Instructor Courses are " + instructor.getCourses());
	}

	private void findCoursesForInstructor(AppDao appDao) {

		int theId = 1;

		System.out.println("Finding the Instructor with ID: " + theId);

		Instructor instructor = appDao.findInstructorById(theId);

		System.out.println("Temp Instructor is " + instructor);

		List<Course> courses = appDao.findCoursesByInstructorId(theId);

		instructor.setCourses(courses);

		System.out.println("Temp Courses are " + instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDao appDao) {

		// create the instructor
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan@luv2code.com");

		InstructorDetail tempInsDetail = new InstructorDetail("http://www.susan.com/youtube", "Video Games");

		tempInstructor.setInstructorDetail(tempInsDetail);

		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("React the Masterclass");

		tempInstructor.addCourse(tempCourse1);
		tempInstructor.addCourse(tempCourse2);

		System.out.println("Saving the Instructor: " + tempInstructor);
		System.out.println("Saving the courses: "+ tempInstructor.getCourses());

		appDao.save(tempInstructor);

		System.out.println("Done!!!");
	}

	private void deleteInstructorDetail(AppDao appDao) {
		int theId = 7;

		System.out.println("Deleting the Instructor Detail with Id" + theId);

		appDao.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDao appDao) {

		int theId=5;

		System.out.println("Finding the Instructor Detail with Id: "+theId);

		InstructorDetail tempInstructorDetail = appDao.findInstructorDetailById(theId);

		System.out.println("Instructor Details are "+tempInstructorDetail);
		System.out.println("and Associated Instructor is "+tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDao appDao) {

		int theId = 1;

		System.out.println("Deleting the instructor with id: " + theId);

		appDao.deleteInstructorById(theId);
	}

	private void findInstructor(AppDao appDao) {
		int theId = 5;

		System.out.println("Finding the instructor with Id: " + theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("Temp Instructor: " +tempInstructor);
		System.out.println("and Associated Instructor Details are: "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDao) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");

		InstructorDetail tempInsDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 Code !!!");

		tempInstructor.setInstructorDetail(tempInsDetail);

		appDao.save(tempInstructor);

		Instructor tempInstructor1 = new Instructor("Hitesh", "Chaudhary", "hitesh@luv2code.com");

		InstructorDetail tempInsDetail1 = new InstructorDetail("http://www.youtube.com/hiteshc", "Front End Coding!");

		tempInstructor1.setInstructorDetail(tempInsDetail1);

		appDao.save(tempInstructor1);
	}
}
