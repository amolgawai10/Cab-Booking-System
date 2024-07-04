package com.cabbooking.cab_booking_system.Repository;

import com.cabbooking.cab_booking_system.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    //syntax :- @Query(value = "---sql query -- =: variable",nativeQuery=true)
    //On this line, now write same method which are available in service layer
    @Query(value = "select * from booking where status =:state", nativeQuery = true)
    public List<Booking> getBookingByStatus(String state);
}
