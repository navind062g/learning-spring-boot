package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDao;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(@Qualifier("appDaoImpl") AppDao appDao) {
		return runner -> {
			//createInstructor(appDao);
			//findInstructor(appDao);
			//deleteInstructor(appDao);
			//findInstructorDetail(appDao);
			deleteInstructorDetail(appDao);
		};
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

		int theId = 4;

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
