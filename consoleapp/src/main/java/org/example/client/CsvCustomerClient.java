package org.example.client;

import org.example.dto.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
public class CsvCustomerClient implements CustomerClient {

    private final WebClient webClient;

    public CsvCustomerClient() {
        //TODO fix this crap url
        this.webClient = WebClient.builder().baseUrl("http://localhost:8080/api/customers")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Override
    public Mono<ResponseEntity<Void>> uploadFile(List<CustomerDto> customerDtos) {
        return webClient.post()
                .uri("/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(customerDtos)
                .retrieve()
                .toBodilessEntity(); // Blocking for simplicity, use subscribe() in a real application
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
