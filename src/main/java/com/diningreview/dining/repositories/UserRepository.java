package com.diningreview.dining.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diningreview.dining.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUserName(String userName);


}