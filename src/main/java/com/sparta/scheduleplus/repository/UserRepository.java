package com.sparta.scheduleplus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparta.scheduleplus.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByOrderByModifiedAtDesc();

	Optional<User> findByEmail(String email);
}
