package com.cabbooking.cab_booking_system.Repository;

import com.cabbooking.cab_booking_system.Models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

}
