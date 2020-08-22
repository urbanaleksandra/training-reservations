package com.diplomski.repository;

import com.diplomski.model.SimpleUser;
import com.diplomski.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {

    SimpleUser findByUser(User user);

    Optional<SimpleUser> findById(Long id);
}
