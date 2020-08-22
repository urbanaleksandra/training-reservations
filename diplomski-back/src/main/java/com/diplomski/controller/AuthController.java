package com.diplomski.controller;

import com.diplomski.dto.UserDTO;
import com.diplomski.model.Role;
import com.diplomski.model.SimpleUser;
import com.diplomski.model.User;
import com.diplomski.model.UserTokenState;
import com.diplomski.registration.VerificationToken;
import com.diplomski.repository.RoleRepository;
import com.diplomski.repository.SimpleUserRepository;
import com.diplomski.repository.VerificationTokenRepository;
import com.diplomski.security.TokenUtils;
import com.diplomski.security.auth.JwtAuthenticationRequest;
import com.diplomski.service.EmailService;
import com.diplomski.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SimpleUserRepository simpleUserRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConversionService conversionService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        System.out.println("usao u login");
        User user = userService.findByUsername(authenticationRequest.getUsername());
        if (user != null && user.isEnabled()) {

            if (BCrypt.checkpw(authenticationRequest.getPassword(), user.getPassword())) {
                logger.info(String.format("Successful login! Username: '%s'.", authenticationRequest.getUsername()));
            } else {
                logger.error(String.format("Incorrect password! Username: '%s'.", authenticationRequest.getUsername()));
                return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.OK);
            }

            if (!user.isEnabled()) {
                return new ResponseEntity<>(new UserTokenState("notActivated", 0), HttpStatus.OK);
            }

            final Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("authentication : " + authentication);

            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            System.out.println("authorities : " + authorities);

            for (GrantedAuthority authority : authorities) {
                System.out.println("Authority: " + authority.getAuthority());
            }

            user = (User) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(user.getUsername());
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));

        } else {
            logger.error(String.format("No user found with username '%s'.", authenticationRequest.getUsername()));
            return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.OK);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userRequest) {

        logger.info("Registration start...");
        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            logger.error(String.format("Username already exists '%s'.", userRequest.getUsername()));
            return new ResponseEntity("Username already exists", HttpStatus.FORBIDDEN);
        }
        User user = mapFromDto(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEnabled(false);
        List<Role> roles = new ArrayList<>();
        Optional<Role> role = roleRepository.findById(3L);
        roles.add(role.get());
        user.setRoles(roles);
        logger.info("Successful registration!");

        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setViolations(0);
        simpleUser.setDeleted(false);
        simpleUser.setUser(user);
        user.setSimpleUser(simpleUser);

        user = userService.save(user);
        simpleUserRepository.save(simpleUser);

        VerificationToken confirmationToken = new VerificationToken(user);
        verificationTokenRepository.save(confirmationToken);

        try {
            emailService.sendNotificaitionAsync(user, confirmationToken);
            logger.info("Registration mail sent to: " + user.getUsername() + " - with confirmation token: " + confirmationToken.getConfirmationToken());
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
            return new ResponseEntity("Email is not valid", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(value="/confirmAccount/{token}")
    public User confirmAccount(@PathVariable("token") String confirmationToken){

        VerificationToken token = verificationTokenRepository.findByConfirmationToken(confirmationToken);
        User user = userService.findByUsername(token.getUser().getUsername());

        user.setEnabled(true);
        user = userService.save(user);
        logger.info("Successfully enabled account for user: " + user.getUsername());

        return token.getUser();
    }

    public User mapFromDto(UserDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setDeleted(false);

        return user;
    }
}
