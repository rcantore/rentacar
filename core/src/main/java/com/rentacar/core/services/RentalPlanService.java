package com.rentacar.core.services;

import com.rentacar.core.entities.HourlyRentalPlan;
import com.rentacar.core.entities.KmRentalPlan;
import com.rentacar.core.entities.RentalPlan;
import com.rentacar.core.entities.WeeklyRentalPlan;
import com.rentacar.core.entities.repositories.HourlyRentalPlanRepository;
import com.rentacar.core.entities.repositories.KmRentalPlanRepository;
import com.rentacar.core.entities.repositories.WeeklyRentalPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalPlanService {

    private final KmRentalPlanRepository kmRentalPlanRepository;

    private final HourlyRentalPlanRepository hourlyRentalPlanRepository;

    private final WeeklyRentalPlanRepository weeklyRentalPlanRepository;

    @Autowired
    public RentalPlanService(KmRentalPlanRepository kmRentalPlanRepository, HourlyRentalPlanRepository hourlyRentalPlanRepository, WeeklyRentalPlanRepository weeklyRentalPlanRepository) {
        this.kmRentalPlanRepository = kmRentalPlanRepository;
        this.hourlyRentalPlanRepository = hourlyRentalPlanRepository;
        this.weeklyRentalPlanRepository = weeklyRentalPlanRepository;
    }

    public RentalPlan getKmRentalPlan() {
        long count = kmRentalPlanRepository.count();
        if (count > 1) {
            return kmRentalPlanRepository.findFirstBy();
        }
        KmRentalPlan rentalPlan = new KmRentalPlan();

        return kmRentalPlanRepository.save(rentalPlan);
    }

    public RentalPlan getHourlyRentalPlan() {
        long count = hourlyRentalPlanRepository.count();
        if (count > 1) {
            return hourlyRentalPlanRepository.findFirstBy();
        }
        HourlyRentalPlan rentalPlan = new HourlyRentalPlan();

        return hourlyRentalPlanRepository.save(rentalPlan);
    }

    public RentalPlan getWeeklyRentalPlan() {
        long count = weeklyRentalPlanRepository.count();
        if (count > 1) {
            return weeklyRentalPlanRepository.findFirstBy();
        }
        WeeklyRentalPlan rentalPlan = new WeeklyRentalPlan();

        return weeklyRentalPlanRepository.save(rentalPlan);
    }
}
