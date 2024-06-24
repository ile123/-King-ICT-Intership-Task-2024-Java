package com.kingict.kingictintership2024java.controller;

import com.kingict.kingictintership2024java.dto.ApiResponse;
import com.kingict.kingictintership2024java.dto.LoginDto;
import com.kingict.kingictintership2024java.dto.ProductDto;
import com.kingict.kingictintership2024java.dto.RegisterDto;
import com.kingict.kingictintership2024java.service.IAuthService;
import com.kingict.kingictintership2024java.service.implementations.DummyJsonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IAuthService authService;
    private final Logger logger = LogManager.getLogger(AuthController.class);
    
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterDto registerDto) {
        try {
            authService.register(registerDto);
            return ResponseEntity
                    .ok(new ApiResponse<>(true,
                            "Registration success!",
                            "User registered successfully"));
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false,
                            "Registration failed!",
                            e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDto loginDto) {
        try {
            var result = authService.authenticate(loginDto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse<>(false,
                            "Authentication failed!",
                            e.getMessage()));
        }
    }
}
