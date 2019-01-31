package com.rentacar.core.services;

import com.rentacar.core.entities.Customer;
import com.rentacar.core.entities.KmRentalPlan;
import com.rentacar.core.entities.RentalSolicitude;
import com.rentacar.core.entities.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentalServiceTest {

    @Autowired
    CarRentalService carRentalService;

    @Test
    public void testSucessfulKmRentalPlan() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        RentalSolicitude rentalSolicitude = carRentalService.rentKmRentalPlanForCustomer(customer);

        assertNotNull(rentalSolicitude);
        assertNotNull(rentalSolicitude.getRentalPlan());
        assertThat(rentalSolicitude.getRentalPlan(), instanceOf(KmRentalPlan.class));

        assertNotNull(rentalSolicitude.getCustomer());
        assertEquals(rentalSolicitude.getCustomer().getName(), customer.getName());

        assertNotNull(rentalSolicitude.getStatus());
        assertEquals(rentalSolicitude.getStatus().getDescription(), Status.STATUS_ON_TRIP);

    }
}
