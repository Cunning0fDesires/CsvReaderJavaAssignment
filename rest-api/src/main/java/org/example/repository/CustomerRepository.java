package org.example.repository;
import org.example.repository.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    CustomerEntity findByCustomerRef(UUID uuid);
}
