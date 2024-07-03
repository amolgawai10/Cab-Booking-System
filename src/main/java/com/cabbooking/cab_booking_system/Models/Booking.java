package com.cabbooking.cab_booking_system.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Booking {
    @Id
    private int id;
    @ManyToOne
    Customer customer;
    private int billAmount;
    private String status;
    private String feedback;
    @ManyToOne
    Driver driver;

}
