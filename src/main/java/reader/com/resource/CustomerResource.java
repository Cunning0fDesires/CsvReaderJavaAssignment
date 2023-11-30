package reader.com.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reader.com.dto.Customer;
import reader.com.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerResource {
    private CustomerService customerService;
    @PostMapping("/upload")
    public void uploadCsv(@RequestBody List<Customer> customers) {
        customerService.saveCustomers(customers);
    }

    @GetMapping("/{customerRef}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customerRef) {
        Customer customer = customerService.getCustomerByRef(customerRef); // replace with actual implementation
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
