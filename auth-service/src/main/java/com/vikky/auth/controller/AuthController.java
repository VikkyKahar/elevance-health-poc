package com.vikky.auth.controller;

import com.vikky.auth.dto.LoginRequestDTO;
import com.vikky.auth.dto.LoginResponseDTO;
import com.vikky.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Auth Service is UP";
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "You are authenticated";
    }

    @GetMapping("/admin")
    public String adminOnly() {
        return "Only ADMIN can access";
    }

    @GetMapping("/user")
    public String userOnly() {
        return "USER or ADMIN can access";
    }

}