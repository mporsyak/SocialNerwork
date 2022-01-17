package com.senla.finalTask.service;

import com.senla.finalTask.model.Role;
import com.senla.finalTask.model.User;
import com.senla.finalTask.repository.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServise implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;


    public UserServise(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDetailsRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user) {
        //logger.info("Add new user: {}", user);
        User userFromDb = userDetailsRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userDetailsRepository.save(user);
        return true;
    }

    public boolean activateUser(String code) {
        User user = userDetailsRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        userDetailsRepository.save(user);
        return true;
    }

    public List<User> findAll() {
        var users = (List<User>) userDetailsRepository.findAll();
        if (users.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return userDetailsRepository.findAll();
    }

    public String create(User user, String username, Map<String, String> form) {
        //logger.info("Save user: {}", user);
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userDetailsRepository.save(user);
        return username;
    }

    public String updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }
        userDetailsRepository.save(user);
        return userEmail;
    }

    public void delete(User user) {
        userDetailsRepository.delete(user);
    }
}
