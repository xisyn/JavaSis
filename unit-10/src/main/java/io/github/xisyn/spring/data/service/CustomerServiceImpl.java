package io.github.xisyn.spring.data.service;

import io.github.xisyn.spring.data.data.CustomerRepository;
import io.github.xisyn.spring.data.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findByFullName(String fullName) {
        return customerRepository.findByFullName(fullName).orElseThrow(() -> new RuntimeException("Клиента с таким ФИО нет"));
    }
}
