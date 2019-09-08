package io.github.xisyn.spring.data.data;

import io.github.xisyn.spring.data.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByFullName(String fullName);
}
