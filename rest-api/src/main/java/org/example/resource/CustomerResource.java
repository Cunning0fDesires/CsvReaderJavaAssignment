package org.example.resource;

import lombok.RequiredArgsConstructor;
import org.example.dto.CustomerDto;
import org.example.repository.CustomerRepository;
import org.example.repository.entities.CustomerEntity;
import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerResource {

    private final CustomerService customerService;
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveCustomer(@RequestBody List<CustomerDto> customers) {
       customerService.saveCustomers(customers);
       return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{customerRef}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerRef") UUID customerRef) {
        Optional<CustomerDto> customer = customerService.findByCustomerReference(customerRef);

        return customer.map(jsonResponse -> new ResponseEntity<>(jsonResponse, HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
