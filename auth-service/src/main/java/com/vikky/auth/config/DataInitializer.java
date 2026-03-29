package com.vikky.auth.config;

import com.vikky.auth.entity.User;
import com.vikky.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.save(new User("vikky", passwordEncoder.encode("1234"), "ADMIN"));
            userRepository.save(new User("john", passwordEncoder.encode("1234"), "USER"));
        };
    }
}