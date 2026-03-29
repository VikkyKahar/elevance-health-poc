package com.vikky.auth.service;

import com.vikky.auth.dto.LoginRequestDTO;
import com.vikky.auth.dto.LoginResponseDTO;
import com.vikky.auth.entity.User;
import com.vikky.auth.exception.InvalidPasswordException;
import com.vikky.auth.exception.UserNotFoundException;
import com.vikky.auth.repository.UserRepository;
import com.vikky.auth.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        // Generate real JWT token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        // Still dummy token (JWT comes next step)
        return new LoginResponseDTO(
                token,
                user.getUsername(),
                user.getRole()
        );
    }
}