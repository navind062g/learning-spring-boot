package com.test.multiple.sources.multiple_data_sources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.multiple.sources.multiple_data_sources.model.user.User;
import com.test.multiple.sources.multiple_data_sources.service.user.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable("id") int id) {
		return userService.findById(id);
	}
}
