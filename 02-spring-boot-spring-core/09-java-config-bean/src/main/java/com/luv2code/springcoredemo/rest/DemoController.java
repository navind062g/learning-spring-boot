package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("aquatic") Coach theCoach)
    {
        System.out.println("In Constructor::"+ this.getClass().getSimpleName());
        this.myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    //define our init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In DoMyStartupStuff "+ this.getClass().getSimpleName());
    }

    //define our destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("In DoMyCleanupStuff:: "+ this.getClass().getSimpleName());
    }
}
