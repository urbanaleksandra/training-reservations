package com.diplomski.controller;

import com.diplomski.dto.ContactFormDTO;
import com.diplomski.dto.UserDTO;
import com.diplomski.model.User;
import com.diplomski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('user')")
    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('user')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/send-message")
    @PreAuthorize("hasAuthority('simple')")
    public ResponseEntity<?> sendMessage(@RequestBody ContactFormDTO contactFormDTO){
        return userService.sendMessage(contactFormDTO);
    }

    @PutMapping(value = "/unblock")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> unblock(@RequestBody String username){
        return userService.unblock(username);
    }
}
