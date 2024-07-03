package com.cabbooking.cab_booking_system.Service;

import com.cabbooking.cab_booking_system.Exceptions.UserNotFoundException;
import com.cabbooking.cab_booking_system.Models.Customer;
import com.cabbooking.cab_booking_system.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    public void registerAccount(Customer customer){
        customerRepository.save(customer);
    }

    public String authenticateCustomer(String email, String password) throws UserNotFoundException {
        Customer customer = customerRepository.findByEmail(email);

        if(customer == null){
            throw new UserNotFoundException(String.format("User with email %s does not exist", email));
        }
        String originalPassword = customer.getPassword();

        if(originalPassword.equals(password)){
            return "Authentication successful";
        }
        return "Authentication Fail";
    }
}
