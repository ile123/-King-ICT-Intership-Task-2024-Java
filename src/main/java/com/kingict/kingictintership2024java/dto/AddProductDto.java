package com.kingict.kingictintership2024java.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddProductDto(String title,
                            float price,
                            String description,
                            String category,
                            String sku,
                            List<String> images) {}
