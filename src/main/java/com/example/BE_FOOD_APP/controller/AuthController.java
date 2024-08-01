package com.example.BE_FOOD_APP.controller;
import com.example.BE_FOOD_APP.dto.JwtResponse;
import com.example.BE_FOOD_APP.equest.LoginRequest;
import com.example.BE_FOOD_APP.model.User;
import com.example.BE_FOOD_APP.security.jwt.JwtUtils;
import com.example.BE_FOOD_APP.security.user.FoodUserDetails;
import com.example.BE_FOOD_APP.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.BE_FOOD_APP.exception.UserAlreadyExistsException;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@CrossOrigin

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registration successful!");

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request){
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(request.getNumberPhone(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
        FoodUserDetails foodUserDetails = (FoodUserDetails) authentication.getPrincipal();
        List<String> roles = foodUserDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(new JwtResponse(
                foodUserDetails.getId(),
                foodUserDetails.getUsername(),
                foodUserDetails.getNumberPhone(),
                jwt,
                roles));
    }

}