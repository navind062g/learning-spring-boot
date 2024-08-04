package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

/*
* Marks the class as Spring bean and class will be part of Spring container.
*
* */
@Component
public class CricketCoach implements Coach
{

    public CricketCoach() {
        System.out.println("In Constructor::"+ this.getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 mins :-)";
    }
}
