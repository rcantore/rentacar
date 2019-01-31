package com.rentacar.core.entities;

import javax.persistence.Entity;

@Entity
public class Customer extends AbstractEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
