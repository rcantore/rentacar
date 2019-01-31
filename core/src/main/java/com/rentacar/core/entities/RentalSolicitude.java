package com.rentacar.core.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class RentalSolicitude extends AbstractEntity {

    @OneToOne
    private Customer customer;

    @OneToOne
    private Status status;

    public RentalPlan getRentalPlan() {
        return new KmRentalPlan();
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
}
