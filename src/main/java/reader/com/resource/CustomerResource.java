package reader.com.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reader.com.dto.Customer;
import reader.com.repository.CustomerRepository;
import reader.com.repository.entities.CustomerEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerResource {

    private final CustomerRepository customerRepository;
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void saveCustomer(@RequestBody List<CustomerEntity> customers) {
       customerRepository.saveAll(customers);
    }

    @RequestMapping(value = "/{customerRef}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerEntity> getCustomer(@PathVariable("customerRef") UUID customerRef) {
        Optional<CustomerEntity> customer = customerRepository.findById(customerRef);

        return customer.map(jsonResponse -> new ResponseEntity<>(jsonResponse, HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
