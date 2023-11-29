package reader.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reader.com.dto.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByCustomerRef(String customerRef);

}
