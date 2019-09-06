package io.github.xisyn.spring.data.service;

import io.github.xisyn.spring.data.entity.Customer;

public interface CustomerService {

    Customer findByFullName(String fullName);
}
