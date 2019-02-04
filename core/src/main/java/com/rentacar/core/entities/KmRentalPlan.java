package com.rentacar.core.entities;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Qualifier("KmRentalPlan")
public class KmRentalPlan extends RentalPlan {
    public static BigDecimal DEFAULT_UNIT_VALUE = BigDecimal.valueOf(50d);

    public KmRentalPlan() {
        super(DEFAULT_UNIT_VALUE);
    }
}
