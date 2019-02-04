package com.rentacar.core.entities.repositories;

import com.rentacar.core.entities.KmRentalPlan;

import javax.transaction.Transactional;

@Transactional
public interface KmRentalPlanRepository extends RentalPlanRepository<KmRentalPlan> {
}
