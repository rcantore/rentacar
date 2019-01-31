package com.rentacar.core.services;

import com.rentacar.core.entities.Customer;
import com.rentacar.core.entities.RentalSolicitude;
import com.rentacar.core.entities.Status;
import org.springframework.stereotype.Service;

@Service
public class CarRentalService {
    public RentalSolicitude rentKmRentalPlanForCustomer(Customer customer) {
        RentalSolicitude rentalSolicitude = new RentalSolicitude();
        rentalSolicitude.setCustomer(customer);
        rentalSolicitude.setStatus(new Status(Status.STATUS_ON_TRIP));
        return rentalSolicitude;
    }
}
