package com.rentacar.core.entities.repositories;

import com.rentacar.core.entities.RentalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RentalPlanRepository<T extends RentalPlan> extends JpaRepository<T, Long> {

    T findFirstBy();

}
