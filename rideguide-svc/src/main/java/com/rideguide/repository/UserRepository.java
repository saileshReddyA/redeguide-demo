package com.rideguide.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rideguide.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByExternalId(String externalId);

    //User findByLoginUserName(String loginUserName);

    User findByEmail(String email);

	User getById(String userId);

	Optional<User> findById(String id);

	void deleteById(String id);

}
