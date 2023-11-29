package reader.com.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reader.com.dto.Customer;

import java.util.List;
@Component
public interface CustomerClient {

    void uploadFile(List<Customer> customers);

    ResponseEntity<Customer> getCustomer(String customerRef);
}