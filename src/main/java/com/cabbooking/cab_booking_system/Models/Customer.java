package com.cabbooking.cab_booking_system.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    //@Entity Annotation : it will convert entity means object into Table in database
    @Id
    private Integer id;
    private Integer age;
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique=true, nullable = false)
    private String email;
    @Column(unique=true, nullable = false)
    private Long phoneNumber;
    private String address;
    @OneToMany(mappedBy = "customer")
    private List<Booking> booking;
}
