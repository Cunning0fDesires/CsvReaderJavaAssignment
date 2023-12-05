package org.example.client;

import org.example.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import reactor.core.Disposable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "customers-service")
public interface CustomerFeignClient {

    Disposable uploadFile(List<CustomerDto> customerDtos);

    ResponseEntity<CustomerDto> getCustomer(UUID customerRef);
}
