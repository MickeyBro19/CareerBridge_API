package com.mickey.careerbridge_api.services;

import com.mickey.careerbridge_api.DTO.RegisterRequest;
import com.mickey.careerbridge_api.Repositories.UserRepository;
import com.mickey.careerbridge_api.exceptions.DuplicateResourceException;
import com.mickey.careerbridge_api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    public User register(RegisterRequest request){
        if(userRepo.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }
        User user =new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        return userRepo.save(user);
    }
}
