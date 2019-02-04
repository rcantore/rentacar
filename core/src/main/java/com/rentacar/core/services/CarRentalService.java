package com.rentacar.core.services;

import com.rentacar.core.entities.*;
import com.rentacar.core.entities.repositories.RentalSolicitudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRentalService {

    @Autowired
    CustomerService customerService;

    final private RentalSolicitudeRepository rentalSolicitudeRepository;

    @Autowired
    public CarRentalService(RentalSolicitudeRepository rentalSolicitudeRepository) {
        this.rentalSolicitudeRepository = rentalSolicitudeRepository;
    }

    public RentalSolicitude createRentalSolicitudeForCustomer(Customer customer) {
        List<RentalSolicitude> rentalSolicitudes = rentalSolicitudeRepository.findAllByCustomer(customer);

        long pendingSolicitudes = rentalSolicitudes.stream().filter(rentalSolicitude ->
                rentalSolicitude.getStatus().getDescription().equals(Status.STATUS_PENDING) ||
                        rentalSolicitude.getStatus().getDescription().equals(Status.STATUS_ON_TRIP)).count();


        RentalSolicitude returnRentalSolicitude = null;
        if (pendingSolicitudes == 0) { // there are no pending trips for customer
            RentalSolicitude rentalSolicitude = new RentalSolicitude();
            rentalSolicitude.setCustomer(customer);
            rentalSolicitude.setStatus(new Status(Status.STATUS_PENDING));

            returnRentalSolicitude = rentalSolicitudeRepository.save(rentalSolicitude);
        }


        return returnRentalSolicitude;
    }

    public RentalSolicitude startTripOfRentalSolicitudeForRentalPlan(RentalSolicitude rentalSolicitude, RentalPlan rentalPlan) {
        rentalSolicitude.setRentalPlan(rentalPlan);
        rentalSolicitude.setStatus(new Status(Status.STATUS_ON_TRIP));

        return rentalSolicitudeRepository.save(rentalSolicitude);
    }

    public RentalSolicitude finalizeTrip(RentalSolicitude inprogressRentalSolicitude, Double kms) {
        inprogressRentalSolicitude.setStatus(new Status(Status.STATUS_TRIP_FINISHED));

        return rentalSolicitudeRepository.save(inprogressRentalSolicitude);
    }
}
