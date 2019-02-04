package com.rentacar.core.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public abstract class RentalPlan extends AbstractEntity{
    public RentalPlan(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    protected BigDecimal unitValue;

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }
}
