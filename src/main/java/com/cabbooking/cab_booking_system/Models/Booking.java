package com.cabbooking.cab_booking_system.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    Customer customer;
    private int billAmount;
    private String status;
    private String feedback;
    @ManyToOne
    Driver driver;
    private String startingLocation;
    private String endingLocation;

}
