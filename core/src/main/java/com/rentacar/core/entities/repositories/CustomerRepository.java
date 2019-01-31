package com.rentacar.core.entities.repositories;

import com.rentacar.core.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByName(String name);

    Customer findCustomerByIdentification(String identification);
}
