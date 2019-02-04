package com.rentacar.core.entities.repositories;

import com.rentacar.core.entities.HourlyRentalPlan;

import javax.transaction.Transactional;

@Transactional
public interface HourlyRentalPlanRepository extends RentalPlanRepository<HourlyRentalPlan> {
}
