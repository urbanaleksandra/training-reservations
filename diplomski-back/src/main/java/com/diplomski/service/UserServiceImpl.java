package com.diplomski.service;

import com.diplomski.dto.ContactFormDTO;
import com.diplomski.dto.UserDTO;
import com.diplomski.model.Role;
import com.diplomski.model.User;
import com.diplomski.repository.RoleRepository;
import com.diplomski.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

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

    @Override
    public ResponseEntity<?> sendMessage(ContactFormDTO contactFormDTO) {
        System.out.println(contactFormDTO);

        try {
            emailService.sendMessage(contactFormDTO);
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
            return new ResponseEntity("Email is not valid", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Poruka poslata", HttpStatus.OK);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<UserDTO> retVal = new ArrayList<>();

        List<User> users = userRepository.findAll();
        for(User user : users){
            if(user.getSimpleUser() != null){
                retVal.add(mapToDto(user));
            }
        }
        return retVal;
    }

    @Override
    public ResponseEntity<?> unblock(String username) {

        User user = userRepository.findByUsername(username);
        user.setEnabled(true);
        user.getSimpleUser().setViolations(0);
        userRepository.save(user);

        return new ResponseEntity<>("Unblock user" + username, HttpStatus.OK);
    }

    public UserDTO mapToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.isEnabled());

        return dto;
    }


}
