package com.rentacar.core.entities;

import javax.persistence.Entity;

@Entity
public class Status extends AbstractEntity {
    public static String STATUS_PENDING = "Pending";
    public static String STATUS_ON_TRIP = "On trip";
    public static String STATUS_TRIP_FINISHED = "Trip finished";
    public static String STATUS_ERROR = "Error";

    private String description;

    public Status() {

    }

    public Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
