package com.kingict.kingictintership2024java.configs;

import com.kingict.kingictintership2024java.enums.Role;
import com.kingict.kingictintership2024java.model.User;
import com.kingict.kingictintership2024java.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestUserInitialization implements CommandLineRunner {
    
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TestUserInitialization(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if(userRepository.findByEmail("test_user@test.com").isEmpty()) {
        var user = User.builder()
                .fullName("Test User")
                .email("test_user@test.com")
                .password(passwordEncoder.encode("test"))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
            userRepository.save(user);
        }
        if(userRepository.findByEmail("test_admin@test.com").isEmpty()) {
            var admin = User.builder()
                    .fullName("Test Admin")
                    .email("test_admin@test.com")
                    .password(passwordEncoder.encode("test"))
                    .role(Role.ADMIN)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            userRepository.save(admin);   
        }
    }
}
