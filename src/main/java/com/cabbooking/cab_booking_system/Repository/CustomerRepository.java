package com.cabbooking.cab_booking_system.Repository;

import com.cabbooking.cab_booking_system.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByEmail(String email);
    //Here jpa will write implementation for this method, it will return Customer object
}
