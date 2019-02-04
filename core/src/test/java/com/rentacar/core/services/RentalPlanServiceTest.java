package com.rentacar.core.services;

import com.rentacar.core.entities.KmRentalPlan;
import com.rentacar.core.entities.RentalPlan;
import com.rentacar.core.entities.repositories.RentalPlanRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalPlanServiceTest {
    @Autowired
    RentalPlanService rentalPlanService;

    @Test
    public void testGetOneKmRentalPlan() {
        RentalPlan rentalPlan = rentalPlanService.getKmRentalPlan();
        assertNotNull(rentalPlan);

        assertThat(rentalPlan, instanceOf(KmRentalPlan.class));
        assertNotNull(rentalPlan.getUnitValues());
        assertEquals(2, rentalPlan.getUnitValues().size());
    }
}
