package com.test.multiple.sources.multiple_data_sources.service.user;

import java.util.List;

import com.test.multiple.sources.multiple_data_sources.model.user.User;

public interface UserService {

	List<User> findAll();
	
	User findById(int id);
	
	User save(User theUser);
	
	void deleteById(int theId);
}
