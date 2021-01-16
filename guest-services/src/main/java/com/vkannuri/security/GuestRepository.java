package com.vkannuri.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** @author Venu Kannuri . */
@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {}
