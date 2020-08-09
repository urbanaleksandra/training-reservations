package com.diplomski.service;

import com.diplomski.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    User findByUsername(String username);

    User save(User user);

    void delete(Long id);

}
