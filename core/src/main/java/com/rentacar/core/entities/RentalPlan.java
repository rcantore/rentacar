package com.rentacar.core.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Map;

@Entity
public abstract class RentalPlan extends AbstractEntity {
    public static final String DEFAULT_UNIT_COST = "DEFAULT_UNIT_COST";
    public static final String DEFAULT_EXCEEDED_UNIT_COST = "DEFAULT_EXCEEDED_UNIT_COST";

    @ElementCollection
    protected Map<String, BigDecimal> unitValues;

    public RentalPlan(Map<String, BigDecimal> unitValues) {
        this.unitValues = unitValues;
    }

    public Map<String, BigDecimal> getUnitValues() {
        return unitValues;
    }

    public void setUnitValues(Map<String, BigDecimal> unitValues) {
        this.unitValues = unitValues;
    }

    public abstract BigDecimal calculateTotalCharge(Map<String, Double> consumedUnits);
}
