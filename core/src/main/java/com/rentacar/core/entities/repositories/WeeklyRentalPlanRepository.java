package com.rentacar.core.entities.repositories;

import com.rentacar.core.entities.WeeklyRentalPlan;

import javax.transaction.Transactional;

@Transactional
public interface WeeklyRentalPlanRepository extends RentalPlanRepository<WeeklyRentalPlan> {
}
