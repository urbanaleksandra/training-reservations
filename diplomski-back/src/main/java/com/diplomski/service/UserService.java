package com.diplomski.service;

import com.diplomski.dto.ContactFormDTO;
import com.diplomski.dto.UserDTO;
import com.diplomski.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    User findByUsername(String username);

    User save(User user);

    void delete(Long id);

    ResponseEntity<?> sendMessage(ContactFormDTO contactFormDTO);

    List<UserDTO> getAllUsers();

    ResponseEntity<?> unblock(String username);

}
