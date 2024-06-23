package com.kingict.kingictintership2024java.service.implementations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingict.kingictintership2024java.dto.AddProductDto;
import com.kingict.kingictintership2024java.service.IExternalApiService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DummyJsonService implements IExternalApiService {

    private final Logger logger = LogManager.getLogger(DummyJsonService.class);
    
    @Override
    public List<AddProductDto> getDataFromApi() {
            var client = HttpClient
                    .newHttpClient();
            
            var request = HttpRequest
                    .newBuilder(URI.create("https://dummyjson.com/products"))
                    .header("accept", "application/json")
                    .build();
            try {
                var responseBody = client
                        .send(request, HttpResponse.BodyHandlers.ofString())
                        .body();
    
                return new ObjectMapper()
                        //fix this line, it breaks here
                        .readValue(responseBody, ProductJsonResponse.class)
                        .getProducts();   
        } catch (Exception e) {
                logger.log(Level.ERROR, e.getMessage());
            return new ArrayList<>();
        }
    }
    
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ProductJsonResponse {
        private List<AddProductDto> products = new ArrayList<>();
    }
}