package com.rentacar.core.entities;

import com.rentacar.core.UnitConstants;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Qualifier("WeeklyRentalPlan")
public class WeeklyRentalPlan extends RentalPlan {
    private static final Map<String, BigDecimal> defaults = new HashMap<>() ;
    public static BigDecimal MY_UNIT_VALUE = BigDecimal.valueOf(10000d);
    public static BigDecimal MY_EXCEED_UNIT_VALUE = BigDecimal.valueOf(300);

    static {
        defaults.put(RentalPlan.DEFAULT_UNIT_COST, MY_UNIT_VALUE);
        defaults.put(RentalPlan.DEFAULT_EXCEEDED_UNIT_COST, MY_EXCEED_UNIT_VALUE);
    }

    public WeeklyRentalPlan() {
        super(defaults);
    }

    @Override
    public BigDecimal calculateTotalCharge(Map<String, Double> consumedUnits) {
        BigDecimal total = getUnitValues().get(RentalPlan.DEFAULT_UNIT_COST)
                .multiply(BigDecimal.valueOf(consumedUnits.get(UnitConstants.WEEKS_KEY)));
        return total;
    }
}
