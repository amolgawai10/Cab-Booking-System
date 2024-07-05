package com.cabbooking.cab_booking_system.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Driver implements AppUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true,nullable = false)
    private String licenceId;
    private String vehicleId;
    private String firstName;
    private String lastName;
    @Column(unique = true,nullable = false)
    private Long phoneNumber;
    @Column(unique = true)
    private String emailId; //emailId name should match with method which is in DriverRepo
    private String password;
    private Double rating;
    private int totalRideServed;
    @OneToMany(mappedBy = "driver")
    private List<Booking> booking;

}
