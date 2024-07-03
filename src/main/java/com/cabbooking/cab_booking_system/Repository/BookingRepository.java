package com.cabbooking.cab_booking_system.Repository;

import com.cabbooking.cab_booking_system.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

}
