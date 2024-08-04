package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    //inject properties for the coach.name and team.name


    @Value("${team.name}")
    private String teamName;

    @Value("${coach.name}")
    private String coachName;


    //expose the new endpoint for teamInfo
    @GetMapping("/teaminfo")
    public String getTeamInfo() {
        return "Coach Name: " + this.coachName + " and Team Name: " + this.teamName;
    }
    //expose a "/" endpoint which will simply retrun a "Hello World"
    @GetMapping("/")
    public String sayHello() {
        return "Hello Cool App World";
    }

    //expose a new endpoint for workout
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5K!!!";
    }

    //expose a new endpoint for fortune
    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your Lucky One!!!";
    }
}
