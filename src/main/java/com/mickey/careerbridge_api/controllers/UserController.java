package com.mickey.careerbridge_api.controllers;

import com.mickey.careerbridge_api.DTO.RegisterRequest;
import com.mickey.careerbridge_api.models.User;
import com.mickey.careerbridge_api.response.ApiResponse;
import com.mickey.careerbridge_api.services.JwtService;
import com.mickey.careerbridge_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody RegisterRequest request){
        User user =service.register(request);

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "User Registered Successfully",
                user
        ));

    }

    @GetMapping("/token-test")
    public String testToken() {
        return jwtService.generateToken("test@gmail.com");
    }
}
