package com.rentacar.core.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class RentalSolicitude extends AbstractEntity {

    @OneToOne
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    private RentalPlan rentalPlan;

    public RentalPlan getRentalPlan() {
        return rentalPlan;
    }

    public void setRentalPlan(RentalPlan rentalPlan) {
        this.rentalPlan = rentalPlan;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotalCharge(Double unitsConsumed) {
        return getRentalPlan().getUnitValue().multiply(BigDecimal.valueOf(unitsConsumed));
    }

}
