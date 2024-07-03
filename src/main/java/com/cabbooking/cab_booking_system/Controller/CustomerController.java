package com.cabbooking.cab_booking_system.Controller;

import com.cabbooking.cab_booking_system.Exceptions.UserNotFoundException;
import com.cabbooking.cab_booking_system.Models.Customer;
import com.cabbooking.cab_booking_system.RequestBody.UserCredentialRequestBody;
import com.cabbooking.cab_booking_system.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @PostMapping("/register")
    public String createAccount(@RequestBody Customer customer){
        customerService.registerAccount(customer);
        return "Account created successfully";
    }

    @GetMapping("/authentication")
    public String loginCustomer(@RequestBody UserCredentialRequestBody userCredentialRequestBody){
        String email = userCredentialRequestBody.getEmail();
        String password = userCredentialRequestBody.getPassword();
        try{
            String authenticationDetails = customerService.authenticateCustomer(email, password);
            return authenticationDetails;
        }catch (UserNotFoundException userNotFoundException){
            return userNotFoundException.getMessage();
        }

    }
}
