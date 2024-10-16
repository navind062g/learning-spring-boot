package com.luv2code.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    //a new controller method to show the initial form
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }


    // need a controller method to process the form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    //need a controller method to read the form data
    //add data to the model
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model theModel) {
        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Yo! " + theName;

        // add message to the model
        theModel.addAttribute("message", result);
        return "helloworld";
    }

    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName,
                                          Model theModel) {
        // read the request parameter from the HTML form to theName

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey, My Friend from V3! " + theName;

        // add message to the model
        theModel.addAttribute("message", result);
        return "helloworld";
    }
}
