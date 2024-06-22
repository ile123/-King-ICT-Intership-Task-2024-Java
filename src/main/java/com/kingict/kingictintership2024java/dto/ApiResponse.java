package com.kingict.kingictintership2024java.dto;

public record ApiResponse<T>(boolean success, String message, T result) {}
