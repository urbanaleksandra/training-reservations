package com.diplomski.service;

import com.diplomski.model.Role;
import com.diplomski.model.User;
import com.diplomski.repository.RoleRepository;
import com.diplomski.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            role.getUsers().remove(user);
            roleRepository.save(role);
        }
        userRepository.delete(user);
    }
}
