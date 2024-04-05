package com.communityconnect.spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.communityconnect.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
