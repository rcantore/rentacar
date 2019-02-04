package com.rentacar.core.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class BigDecimalEntityWrapper extends AbstractEntity {
    BigDecimal value;

    public BigDecimalEntityWrapper() {
    }

    public BigDecimalEntityWrapper(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
