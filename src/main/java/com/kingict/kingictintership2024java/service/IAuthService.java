package com.kingict.kingictintership2024java.service;

import com.kingict.kingictintership2024java.dto.ApiResponse;
import com.kingict.kingictintership2024java.dto.LoginDto;
import com.kingict.kingictintership2024java.dto.RegisterDto;

public interface IAuthService {
    void register(RegisterDto registerDto);
    ApiResponse<String> authenticate(LoginDto registerDto);
}
