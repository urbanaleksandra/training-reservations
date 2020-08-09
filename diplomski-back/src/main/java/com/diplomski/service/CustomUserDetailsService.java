package com.diplomski.service;

import com.diplomski.model.Permission;
import com.diplomski.model.Role;
import com.diplomski.model.User;
import com.diplomski.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            LOGGER.error(String.format("No user found with username '%s'.", username));
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return getGrantedAuthorities(getPermissions(role));
    }

    private Collection<Permission> getPermissions(Role role) {
        return role.getPermission();
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<Permission> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Permission p : permissions) {
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        }
        return authorities;
    }

}
