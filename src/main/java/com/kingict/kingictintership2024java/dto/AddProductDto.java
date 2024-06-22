package com.kingict.kingictintership2024java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AddProductDto(@JsonProperty("title") String title,
                            @JsonProperty("price") float price,
                            @JsonProperty("description") String description,
                            @JsonProperty("category") String category,
                            @JsonProperty("sku") String sku,
                            @JsonProperty("images") List<String> images) {}
