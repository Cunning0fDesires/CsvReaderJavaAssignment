package org.example.resource;

import org.example.dto.CustomerDto;
import org.example.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerResource {

    private final CustomerService customerService;
    private static final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public List<CustomerDto> saveCustomers(@RequestBody List<CustomerDto> customers) {
        log.info("Saving the customers to DB");
        customerService.saveCustomers(customers);
        return customers;
    }

    @GetMapping(value = "/{customerRef}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto getCustomer(@PathVariable("customerRef") UUID customerRef) {
        log.info("Retrieving the customer UUID: " + customerRef + " from database");
        return customerService.findByCustomerReference(customerRef);
    }
}
