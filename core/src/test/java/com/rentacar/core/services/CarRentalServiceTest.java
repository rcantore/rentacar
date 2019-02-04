package com.rentacar.core.services;

import com.rentacar.core.entities.Customer;
import com.rentacar.core.entities.KmRentalPlan;
import com.rentacar.core.entities.RentalSolicitude;
import com.rentacar.core.entities.Status;
import com.rentacar.core.entities.repositories.CustomerRepository;
import com.rentacar.core.entities.repositories.RentalSolicitudeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentalServiceTest {

    @Autowired
    CarRentalService carRentalService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RentalSolicitudeRepository rentalSolicitudeRepository;

    @Before
    public void setUp() {
        rentalSolicitudeRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testRejectSolicitudeCustomerWithOngoingTrip() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setIdentification("9999999");

        Customer savedCustomer = customerService.save(customer);
        RentalSolicitude rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(savedCustomer);

        assertNotNull(rentalSolicitude);

        rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(savedCustomer);

        assertNull(rentalSolicitude);

    }

    @Test
    public void testSuccessfulCreationKmRentalPlan() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setIdentification("9999999");

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
    public void testSucessfulTripEndKmRentalPlan() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setIdentification("9999999");

        RentalSolicitude rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(customerService.save(customer));

        assertNotNull(rentalSolicitude);

        assertNotNull(rentalSolicitude.getCustomer());
        assertEquals(rentalSolicitude.getCustomer().getName(), customer.getName());

        assertNotNull(rentalSolicitude.getStatus());
        assertEquals(Status.STATUS_PENDING, rentalSolicitude.getStatus().getDescription());

        RentalSolicitude inProgressRentalSolicitude = carRentalService.startTripOfRentalSolicitudeForRentalPlan(rentalSolicitude, new KmRentalPlan());

        assertNotNull(inProgressRentalSolicitude.getStatus());
        assertEquals(Status.STATUS_ON_TRIP, inProgressRentalSolicitude.getStatus().getDescription());

        Double kms = 500d;
        RentalSolicitude finalizedRentalSolicitude = carRentalService.finalizeTrip(inProgressRentalSolicitude, kms);
        assertNotNull(finalizedRentalSolicitude);

        assertEquals(Status.STATUS_TRIP_FINISHED, finalizedRentalSolicitude.getStatus().getDescription());

        // expectd 500km * $50 = $25000
        Double unitsConsumed = kms;
        assertTrue(finalizedRentalSolicitude.getTotalCharge(unitsConsumed).compareTo(new BigDecimal(25000d)) == 0);

    }
}
