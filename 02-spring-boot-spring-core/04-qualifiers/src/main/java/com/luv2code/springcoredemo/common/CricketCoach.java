package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/*
* Marks the class as Spring bean and class will be part of Spring container.
*
* */
@Component
@Primary
public class CricketCoach implements Coach
{
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 mins :-)";
    }
}
