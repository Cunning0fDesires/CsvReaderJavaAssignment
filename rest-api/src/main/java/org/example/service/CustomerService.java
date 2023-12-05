package org.example.service;

import org.example.dto.CustomerDto;
import org.example.mappers.CustomerMapper;
import org.example.repository.CustomerRepository;
import org.example.repository.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomers(List<CustomerDto> customers) {
        List<CustomerEntity> entities = CustomerMapper.INSTANCE.toEntityList(customers);
        customerRepository.saveAll(entities);
        log.info("Customers were saved successfully!");
    }

    public CustomerDto findByCustomerReference(UUID customerRef) {
        log.info("Looking for the customer that have UUID: " + customerRef);
        CustomerEntity customer = customerRepository.findByCustomerRef(customerRef);
        return CustomerMapper.INSTANCE.toDTO(customer);
    }
}
