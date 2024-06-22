package com.kingict.kingictintership2024java.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kingict.kingictintership2024java.dto.AddProductDto;

import java.io.IOException;
import java.util.List;

public interface IExternalApiService {
    List<AddProductDto> getDataFromApi() throws IOException, InterruptedException;
}
