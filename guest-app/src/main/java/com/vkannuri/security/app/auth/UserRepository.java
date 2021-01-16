package com.vkannuri.security.app.auth;

import org.springframework.data.jpa.repository.JpaRepository;

/** @author Venu Kannuri . */
public interface UserRepository extends JpaRepository<User, Long> {
  User findAllByUserName(String userName);
}
