package com.test.multiple.sources.multiple_data_sources.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.multiple.sources.multiple_data_sources.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
