package com.mickey.careerbridge_api.controllers;

import com.mickey.careerbridge_api.DTO.RegisterRequest;
import com.mickey.careerbridge_api.models.User;
import com.mickey.careerbridge_api.response.ApiResponse;
import com.mickey.careerbridge_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody RegisterRequest request){
        User user =service.register(request);

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "User Registered Successfully",
                user
        ));

    }
}
