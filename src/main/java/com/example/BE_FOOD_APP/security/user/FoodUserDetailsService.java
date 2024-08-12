package com.example.BE_FOOD_APP.security.user;

import com.example.BE_FOOD_APP.respo.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.BE_FOOD_APP.model.User;

@Service
@RequiredArgsConstructor
public class FoodUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String numberPhone) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(numberPhone)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return FoodUserDetails.buildUserDetails(user);
    }
}