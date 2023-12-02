package org.example.client;

import org.example.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "customersservice")
public interface CustomerClient {

    Mono<ResponseEntity<Void>> uploadFile(List<CustomerDto> customerDtos);

    ResponseEntity<CustomerDto> getCustomer(UUID customerRef);
}
