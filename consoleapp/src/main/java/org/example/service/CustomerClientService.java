package org.example.service;

import org.example.client.CustomerFeignClient;
import org.example.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerClientService {

    private final CustomerFeignClient customerFeignClient;

    public CustomerClientService(CustomerFeignClient customerFeignClient) {
        this.customerFeignClient = customerFeignClient;
    }

    public void uploadCustomer(List<CustomerDto> customerDtos) {
        customerFeignClient.uploadFile(customerDtos);
    }

    public void getCustomerByRef(UUID customerRef) {
        ResponseEntity<CustomerDto> response = customerFeignClient.getCustomer(customerRef);
        if (response.getStatusCode().is2xxSuccessful()) {
            CustomerDto customerDto = response.getBody();
            System.out.println("Customer Details: " + customerDto);
        } else if (response.getStatusCode().is4xxClientError()) {
            System.out.println("Customer not found");
        } else {
            System.out.println("Error retrieving customer");
        }
    }
}
