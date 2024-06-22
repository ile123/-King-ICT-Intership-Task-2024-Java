package com.kingict.kingictintership2024java.dto;

import java.util.List;
import java.util.UUID;

public record ProductDto(UUID id, String title, float price, String description, String category, String sku, List<String> images) {}
