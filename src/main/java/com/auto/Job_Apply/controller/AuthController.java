package com.auto.Job_Apply.controller;

import com.auto.Job_Apply.dto.request.LoginRequest;
import com.auto.Job_Apply.dto.request.LoginResponse;
import com.auto.Job_Apply.dto.request.RegisterRequest;
import com.auto.Job_Apply.dto.request.UserDto;
import com.auto.Job_Apply.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
}
