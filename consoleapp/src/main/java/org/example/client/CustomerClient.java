package org.example.client;

import org.example.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.Disposable;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerClient implements CustomerFeignClient {

    private final WebClient webClient;
    private static final Logger log = LoggerFactory.getLogger(CustomerClient.class);
    private static String BASE_URL = "http://rest-api:8080/api/customers";
    private static final String HOST = "rest-api:8080";

    public CustomerClient() {
        this.webClient = WebClient.builder().baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.HOST, HOST)
                .build();
    }

    @Override
    public Disposable uploadFile(List<CustomerDto> customerDtos) {
        log.info("That's the BASE URL: " + BASE_URL);
        return webClient.post()
                .bodyValue(customerDtos)
                .retrieve()
                .toBodilessEntity()
                .subscribe(
                        responseEntity -> {
                            log.info("Status Code: " + responseEntity.getStatusCode().value());
                            log.info("Location : " + responseEntity.getHeaders().getLocation());
                        },
                        error -> {
                            if (error instanceof WebClientResponseException ex) {
                                log.error("Error Status Code: " + ex.getStatusCode().value());
                                log.error("Message: " + ex.getMessage());
                                log.error("Response Body: " + ex.getResponseBodyAsString());
                                log.error("Status text: " + ex.getStatusText());
                            } else {
                                log.error("An unexpected error occurred: " + error.getMessage());
                            }
                        }
                );
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomer(UUID customerRef) {
        return webClient.get()
                .uri("/{customerRef}", customerRef)
                .retrieve()
                .toEntity(CustomerDto.class)
                .block(); // Blocking for simplicity, use subscribe() in a real application
    }
}
