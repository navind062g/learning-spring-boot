package com.test.multiple.sources.multiple_data_sources.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.multiple.sources.multiple_data_sources.dao.user.UserRepository;
import com.test.multiple.sources.multiple_data_sources.model.user.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		Optional<User> userObj = this.userRepository.findById(id);
		
		if(userObj.isEmpty()) {
			throw new RuntimeException("User with id " + id + " not found");
		}
		
		return userObj.get();
	}

	@Override
	public User save(User theUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

}
