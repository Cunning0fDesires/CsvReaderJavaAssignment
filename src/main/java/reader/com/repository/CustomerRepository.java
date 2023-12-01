package reader.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reader.com.dto.Customer;
import reader.com.repository.entities.CustomerEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
