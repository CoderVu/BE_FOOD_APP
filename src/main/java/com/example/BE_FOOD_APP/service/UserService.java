package com.example.BE_FOOD_APP.service;

import com.example.BE_FOOD_APP.exception.UserAlreadyExistsException;
import com.example.BE_FOOD_APP.model.Role;
import com.example.BE_FOOD_APP.respository.RoleRepository;
import com.example.BE_FOOD_APP.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.BE_FOOD_APP.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(User user) {
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_ADMIN");
        if (!optionalRole.isPresent()) {
            throw new RuntimeException("ROLE_USER not found");
        }
        Role userRole = optionalRole.get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }
}