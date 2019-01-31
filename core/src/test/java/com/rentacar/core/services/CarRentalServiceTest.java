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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentalServiceTest {

    @Autowired
    CarRentalService carRentalService;

    @Autowired
    CustomerService customerService;

    @Test
    public void testSucessfulKmRentalPlan() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        RentalSolicitude rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(customerService.save(customer));

        assertNotNull(rentalSolicitude);

        assertNotNull(rentalSolicitude.getCustomer());
        assertEquals(rentalSolicitude.getCustomer().getName(), customer.getName());

        assertNotNull(rentalSolicitude.getStatus());
        assertEquals(Status.STATUS_PENDING, rentalSolicitude.getStatus().getDescription());

        RentalSolicitude modifiedRentalSolicitude = carRentalService.startTripOfRentalSolicitudeForRentalPlan(rentalSolicitude, new KmRentalPlan());

        assertNotNull(modifiedRentalSolicitude.getStatus());
        assertEquals(Status.STATUS_ON_TRIP, modifiedRentalSolicitude.getStatus().getDescription());

    }

    @Test
    public void testRejectSolicitudeCustomerWithOngoingTrip() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        Customer savedCustomer = customerService.save(customer);
        RentalSolicitude rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(savedCustomer);

        assertNotNull(rentalSolicitude);

        rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(savedCustomer);

        assertNull(rentalSolicitude);

    }
}
