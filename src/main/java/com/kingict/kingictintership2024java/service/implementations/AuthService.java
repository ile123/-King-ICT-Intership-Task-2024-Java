package com.kingict.kingictintership2024java.service.implementations;

import com.kingict.kingictintership2024java.configs.JwtService;
import com.kingict.kingictintership2024java.dto.ApiResponse;
import com.kingict.kingictintership2024java.dto.LoginDto;
import com.kingict.kingictintership2024java.dto.RegisterDto;
import com.kingict.kingictintership2024java.enums.Role;
import com.kingict.kingictintership2024java.model.User;
import com.kingict.kingictintership2024java.repository.IUserRepository;
import com.kingict.kingictintership2024java.service.IAuthService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService implements IAuthService {
    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Logger logger = LogManager.getLogger(AuthService.class);

    public AuthService(
            IUserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterDto registerDto) {
        if(userRepository.findByEmail(registerDto.email()).isPresent()) return;
        var user = User.builder()
                .fullName(registerDto.fullName())
                .email(registerDto.email())
                .password(passwordEncoder.encode(registerDto.password()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    public ApiResponse<String> authenticate(LoginDto registerDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registerDto.email(),
                            registerDto.password()
                    )
            );

            var user = userRepository
                    .findByEmail(registerDto.email())
                    .orElseThrow(() -> new UsernameNotFoundException(registerDto.email()));
            String jwtToken = jwtService.generateToken(user);
            return new ApiResponse<>(true, "Authentication Successful", jwtToken);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
            return new ApiResponse<>(false, e.getMessage(), "");
        }
    }
}
