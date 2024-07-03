package com.cabbooking.cab_booking_system.Controller;

import com.cabbooking.cab_booking_system.Models.Driver;
import com.cabbooking.cab_booking_system.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;
    @PostMapping("register")
    public String createAccount(@RequestBody Driver driver){
        //call service layer
        driverService.registerDriver(driver);
        return "Driver is successfully registered";
    }
}
