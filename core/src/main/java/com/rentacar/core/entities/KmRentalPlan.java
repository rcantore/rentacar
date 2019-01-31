package com.rentacar.core.entities;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Entity;

@Entity
@Qualifier("KmRentalPlan")
public class KmRentalPlan  extends AbstractEntity implements RentalPlan {

}
