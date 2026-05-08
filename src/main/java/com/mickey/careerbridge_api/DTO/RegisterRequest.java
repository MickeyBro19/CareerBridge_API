package com.mickey.careerbridge_api.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Invalid Email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 4,max = 8, message = "Password should be between 4-8 characters")
    private String password;
}
