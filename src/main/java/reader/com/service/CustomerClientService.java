package reader.com.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reader.com.client.CustomerClient;
import reader.com.dto.Customer;

import java.util.List;

@Component
public class CustomerClientService {

    private final CustomerClient customerClient;

    public CustomerClientService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public void uploadCustomer(List<Customer> customers) {
        customerClient.uploadFile(customers);
    }

    public void getCustomerByRef(String customerRef) {
        ResponseEntity<Customer> response = customerClient.getCustomer(customerRef);
        if (response.getStatusCode().is2xxSuccessful()) {
            Customer customer = response.getBody();
            System.out.println("Customer Details: " + customer);
        } else if (response.getStatusCode().is4xxClientError()) {
            System.out.println("Customer not found");
        } else {
            System.out.println("Error retrieving customer");
        }
    }
}
