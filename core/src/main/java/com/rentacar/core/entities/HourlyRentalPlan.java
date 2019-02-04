package com.rentacar.core.entities;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Qualifier("HourlyRentalPlan")
public class HourlyRentalPlan extends RentalPlan {
    public static BigDecimal DEFAULT_UNIT_VALUE = BigDecimal.valueOf(300d);

    public HourlyRentalPlan() {
        super(DEFAULT_UNIT_VALUE);
    }

    public HourlyRentalPlan(BigDecimal unitValue) {
        super(unitValue);
    }
}
