package com.rentacar.core.services;

import com.rentacar.core.entities.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        Customer savedCustomer = customerService.save(customer);

        assertNotNull(savedCustomer);
    }

    @Test
    public void testFindCustomerByIdentification() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setIdentification("9999999");

        Customer savedCustomer = customerService.save(customer);

        assertNotNull(savedCustomer);

        Customer foundCustomer = customerService.findByIdentification(customer.getIdentification());
        assertNotNull(foundCustomer);
        assertEquals(savedCustomer.getName(), foundCustomer.getName());
        assertEquals(savedCustomer.getId(), foundCustomer.getId());

    }

}
