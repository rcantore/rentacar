package com.rentacar.core.services;

import com.rentacar.core.entities.Customer;
import com.rentacar.core.entities.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    final private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }


    public Customer findByIdentification(String identification) {
        return customerRepository.findCustomerByIdentification(identification);
    }

}
