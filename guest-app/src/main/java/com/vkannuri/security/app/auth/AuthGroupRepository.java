package com.vkannuri.security.app.auth;

import com.vkannuri.security.app.domain.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** @author Venu Kannuri . */
public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
    List<AuthGroup> findAllByUsername(String userName);
}
