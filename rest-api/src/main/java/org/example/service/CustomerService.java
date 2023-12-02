package org.example.service;

import org.example.dto.CustomerDto;
import org.example.mappers.CustomerMapper;
import org.example.repository.CustomerRepository;
import org.example.repository.entities.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomers(List<CustomerDto> customers) {
        List<CustomerEntity> entities = CustomerMapper.INSTANCE.toEntityList(customers);
        customerRepository.saveAll(entities);
    }

    public Optional<CustomerDto> findByCustomerReference(UUID customerRef) {
        Optional<CustomerEntity> customer = customerRepository.findById(customerRef);
        return customer.map(CustomerMapper.INSTANCE::toDTO);
    }
}
