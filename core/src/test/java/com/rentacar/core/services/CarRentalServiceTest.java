package com.rentacar.core.services;

import com.rentacar.core.UnitConstants;
import com.rentacar.core.entities.Customer;
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
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentalServiceTest {

    @Autowired
    CarRentalService carRentalService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RentalPlanService rentalPlanService;

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

        RentalSolicitude modifiedRentalSolicitude = carRentalService.startTripOfRentalSolicitudeForRentalPlan(rentalSolicitude, rentalPlanService.getKmRentalPlan());

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

        RentalSolicitude inProgressRentalSolicitude = carRentalService.startTripOfRentalSolicitudeForRentalPlan(rentalSolicitude, rentalPlanService.getKmRentalPlan());

        assertNotNull(inProgressRentalSolicitude.getStatus());
        assertEquals(Status.STATUS_ON_TRIP, inProgressRentalSolicitude.getStatus().getDescription());

        Double kms = 500d;
        RentalSolicitude finalizedRentalSolicitude = carRentalService.finalizeTrip(inProgressRentalSolicitude, kms);
        assertNotNull(finalizedRentalSolicitude);

        assertEquals(Status.STATUS_TRIP_FINISHED, finalizedRentalSolicitude.getStatus().getDescription());

        // expectd 500km * $50 = $25000
        Map<String, Double> unitsConsumed = new HashMap<String, Double>() {{
            put(UnitConstants.KMS_KEY, kms);
        }};
        assertTrue(finalizedRentalSolicitude.getTotalCharge(unitsConsumed).compareTo(new BigDecimal(25000d)) == 0);

    }


    @Test
    public void testSuccessfulCreationHourlyRentalPlan() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setIdentification("9999999");

        RentalSolicitude rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(customerService.save(customer));

        assertNotNull(rentalSolicitude);

        assertNotNull(rentalSolicitude.getCustomer());
        assertEquals(rentalSolicitude.getCustomer().getName(), customer.getName());

        assertNotNull(rentalSolicitude.getStatus());
        assertEquals(Status.STATUS_PENDING, rentalSolicitude.getStatus().getDescription());

        RentalSolicitude modifiedRentalSolicitude = carRentalService.startTripOfRentalSolicitudeForHourlyRentalPlan(rentalSolicitude, rentalPlanService.getHourlyRentalPlan());

        assertNotNull(modifiedRentalSolicitude.getStatus());
        assertEquals(Status.STATUS_ON_TRIP, modifiedRentalSolicitude.getStatus().getDescription());

    }

    @Test
    public void testSucessfulTripEndHourlyRentalPlan() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setIdentification("9999999");

        RentalSolicitude rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(customerService.save(customer));

        assertNotNull(rentalSolicitude);

        assertNotNull(rentalSolicitude.getCustomer());
        assertEquals(rentalSolicitude.getCustomer().getName(), customer.getName());

        assertNotNull(rentalSolicitude.getStatus());
        assertEquals(Status.STATUS_PENDING, rentalSolicitude.getStatus().getDescription());

        RentalSolicitude inProgressRentalSolicitude = carRentalService.startTripOfRentalSolicitudeForHourlyRentalPlan(rentalSolicitude, rentalPlanService.getHourlyRentalPlan());

        assertNotNull(inProgressRentalSolicitude.getStatus());
        assertEquals(Status.STATUS_ON_TRIP, inProgressRentalSolicitude.getStatus().getDescription());

        Double hours = 6d;
        RentalSolicitude finalizedRentalSolicitude = carRentalService.finalizeTrip(inProgressRentalSolicitude, hours);
        assertNotNull(finalizedRentalSolicitude);

        assertEquals(Status.STATUS_TRIP_FINISHED, finalizedRentalSolicitude.getStatus().getDescription());

        // expectd 6hrs * $300 = $1800
        Map<String, Double> unitsConsumed = new HashMap<String, Double>() {{
            put(UnitConstants.HOURS_KEY, hours);
        }};
        assertTrue(finalizedRentalSolicitude.getTotalCharge(unitsConsumed).compareTo(new BigDecimal(1800d)) == 0);

    }

    @Test
    public void testSuccessfulCreationWeeklyRentalPlan() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setIdentification("9999999");

        RentalSolicitude rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(customerService.save(customer));

        assertNotNull(rentalSolicitude);

        assertNotNull(rentalSolicitude.getCustomer());
        assertEquals(rentalSolicitude.getCustomer().getName(), customer.getName());

        assertNotNull(rentalSolicitude.getStatus());
        assertEquals(Status.STATUS_PENDING, rentalSolicitude.getStatus().getDescription());

        RentalSolicitude modifiedRentalSolicitude = carRentalService.startTripOfRentalSolicitudeForWeeklyRentalPlan(rentalSolicitude, rentalPlanService.getWeeklyRentalPlan());

        assertNotNull(modifiedRentalSolicitude.getStatus());
        assertEquals(Status.STATUS_ON_TRIP, modifiedRentalSolicitude.getStatus().getDescription());

    }

    @Test
    public void testSuccessfulTripEndWeeklyRentalPlan() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setIdentification("9999999");

        RentalSolicitude rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(customerService.save(customer));

        assertNotNull(rentalSolicitude);

        assertNotNull(rentalSolicitude.getCustomer());
        assertEquals(rentalSolicitude.getCustomer().getName(), customer.getName());

        assertNotNull(rentalSolicitude.getStatus());
        assertEquals(Status.STATUS_PENDING, rentalSolicitude.getStatus().getDescription());

        RentalSolicitude inProgressRentalSolicitude = carRentalService.startTripOfRentalSolicitudeForWeeklyRentalPlan(rentalSolicitude, rentalPlanService.getWeeklyRentalPlan());

        assertNotNull(inProgressRentalSolicitude.getStatus());
        assertEquals(Status.STATUS_ON_TRIP, inProgressRentalSolicitude.getStatus().getDescription());

        Double weeks = 2d; //two weeks
        RentalSolicitude finalizedRentalSolicitude = carRentalService.finalizeTrip(inProgressRentalSolicitude, weeks);
        assertNotNull(finalizedRentalSolicitude);

        assertEquals(Status.STATUS_TRIP_FINISHED, finalizedRentalSolicitude.getStatus().getDescription());

        // expectd 2w * $10000 = $20000
        Map<String, Double> unitsConsumed = new HashMap<String, Double>() {{
            put(UnitConstants.WEEKS_KEY, weeks);
        }};

        assertEquals(0, finalizedRentalSolicitude.getTotalCharge(unitsConsumed).compareTo(new BigDecimal(20000d)));

    }

    @Test
    public void testSucessfulTripEndExceededWeeklyRentalPlan() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setIdentification("9999999");

        RentalSolicitude rentalSolicitude = carRentalService.createRentalSolicitudeForCustomer(customerService.save(customer));

        assertNotNull(rentalSolicitude);

        assertNotNull(rentalSolicitude.getCustomer());
        assertEquals(rentalSolicitude.getCustomer().getName(), customer.getName());

        assertNotNull(rentalSolicitude.getStatus());
        assertEquals(Status.STATUS_PENDING, rentalSolicitude.getStatus().getDescription());

        RentalSolicitude inProgressRentalSolicitude = carRentalService.startTripOfRentalSolicitudeForWeeklyRentalPlan(rentalSolicitude, rentalPlanService.getWeeklyRentalPlan());

        assertNotNull(inProgressRentalSolicitude.getStatus());
        assertEquals(Status.STATUS_ON_TRIP, inProgressRentalSolicitude.getStatus().getDescription());

        Double weeks = 2d; //two weeks
        Double kms = 3300d; // 300 kms exceeded
        RentalSolicitude finalizedRentalSolicitude = carRentalService.finalizeTrip(inProgressRentalSolicitude, weeks);
        assertNotNull(finalizedRentalSolicitude);

        assertEquals(Status.STATUS_TRIP_FINISHED, finalizedRentalSolicitude.getStatus().getDescription());

        // expectd (2w * $10000) + ((30km / 3km) * $100) = $20000 + $10000 = $30000
        Map<String, Double> unitsConsumed = new HashMap<String, Double>() {{
            put(UnitConstants.WEEKS_KEY, weeks);
        }};
        assertEquals(0, finalizedRentalSolicitude.getTotalCharge(unitsConsumed).compareTo(new BigDecimal(30000d)));

    }
}
