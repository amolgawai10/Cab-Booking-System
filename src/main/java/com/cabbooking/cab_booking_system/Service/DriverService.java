package com.cabbooking.cab_booking_system.Service;

import com.cabbooking.cab_booking_system.Models.Driver;
import com.cabbooking.cab_booking_system.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    public void registerDriver(Driver driver){
        driverRepository.save(driver);
    }
}
