package com.mickey.careerbridge_api.services;

import com.mickey.careerbridge_api.DTO.AuthResponse;
import com.mickey.careerbridge_api.DTO.LoginRequest;
import com.mickey.careerbridge_api.DTO.RegisterRequest;
import com.mickey.careerbridge_api.Repositories.UserRepository;
import com.mickey.careerbridge_api.exceptions.DuplicateResourceException;
import com.mickey.careerbridge_api.exceptions.InvalidCredentialsException;
import com.mickey.careerbridge_api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    public User register(RegisterRequest request){
        if(userRepo.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }
        User user =new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        return userRepo.save(user);
    }

    public AuthResponse login(LoginRequest request){
        User user =userRepo.findByEmail(request.getEmail())
                .orElseThrow(()-> new InvalidCredentialsException("Invalid Credentials"));

        if(
                !passwordEncoder.matches(request.getPassword(),user.getPassword())
        ) throw  new InvalidCredentialsException("Invalid Credentials");

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token
        );

    }
}
