package reader.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reader.com.dto.Customer;
import reader.com.repository.CustomerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomers(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }

    public Customer getCustomerByRef(String customerRef) {
        return customerRepository.findByCustomerRef(customerRef);
    }

}
