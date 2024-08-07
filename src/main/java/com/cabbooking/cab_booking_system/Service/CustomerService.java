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

    public Customer getCustomerById(int customerId){
        return customerRepository.findById(customerId).orElse(null);
        //if customer exist return object otherwise return 'null'
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

    //Find the customer in database by his emailId
    public Customer getCustomerByEmailId(String email){
        return customerRepository.findByEmail(email);
    }
}
