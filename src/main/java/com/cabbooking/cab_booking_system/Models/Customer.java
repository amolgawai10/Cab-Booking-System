package com.cabbooking.cab_booking_system.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer implements AppUser{

    //@Entity Annotation : it will convert entity means object into Table in database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Booking> booking;
}
