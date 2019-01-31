package com.rentacar.core.entities.repositories;

import com.rentacar.core.entities.Customer;
import com.rentacar.core.entities.RentalSolicitude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalSolicitudeRepository extends JpaRepository<RentalSolicitude, Long> {
    List<RentalSolicitude> findAllByCustomer(Customer customer);
}
