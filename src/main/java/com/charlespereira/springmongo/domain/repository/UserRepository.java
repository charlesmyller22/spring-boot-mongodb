package com.charlespereira.springmongo.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.charlespereira.springmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	
}
