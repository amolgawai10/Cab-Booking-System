package com.cabbooking.cab_booking_system.Service;

import com.cabbooking.cab_booking_system.Exceptions.InvalidOperationException;
import com.cabbooking.cab_booking_system.Exceptions.ResourceDoesNotExistException;
import com.cabbooking.cab_booking_system.Exceptions.UserNotFoundException;
import com.cabbooking.cab_booking_system.Models.AppUser;
import com.cabbooking.cab_booking_system.Models.Booking;
import com.cabbooking.cab_booking_system.Models.Customer;
import com.cabbooking.cab_booking_system.Models.Driver;
import com.cabbooking.cab_booking_system.Repository.BookingRepository;
import com.cabbooking.cab_booking_system.ResponseBody.BookingResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    CustomerService customerService;
    @Autowired
    DriverService driverService;
    @Autowired
    BookingRepository bookingRepository;

    public void handleCustomerRequest(String startingLocation, String endingLocation, int customerId) throws UserNotFoundException {
        //check customer is valid or not
        Customer customer = customerService.getCustomerById(customerId);
        if(customer == null){
            throw new UserNotFoundException(String.format("User with id %d does not exist",customerId));
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setStatus("DRAFT"); // Booking is created not accepted by driver yet
        booking.setBillAmount(0);
        booking.setStartingLocation(startingLocation);
        booking.setEndingLocation(endingLocation);

        bookingRepository.save(booking);

    }

    public List<BookingResponseBody> getBookingByStatus(String state){
        //Apart from jpa method we should make our own query
        //Now go to Booking repository and write sql query there
        //now just call repo and return list
        List<Booking> bookingList = bookingRepository.getBookingByStatus(state);
        List<BookingResponseBody> bookingResponseBodyList = new ArrayList<>();
        for(Booking booking : bookingList){
            BookingResponseBody bookingResponseBody = new BookingResponseBody();
            bookingResponseBody.setBookingId(booking.getId());
            bookingResponseBody.setCustomerId(booking.getCustomer().getId());
            bookingResponseBody.setStatus(booking.getStatus());
            bookingResponseBody.setStartingLocation(booking.getStartingLocation());
            bookingResponseBody.setEndingLocation(booking.getEndingLocation());
            bookingResponseBody.setCustomerName(booking.getCustomer().getFirstName());
            bookingResponseBody.setBillAmount(booking.getBillAmount());
            bookingResponseBodyList.add(bookingResponseBody);
        }
        return bookingResponseBodyList;
    }

    public String updateBooking(String operation, String emailId, Integer bookingId) throws UserNotFoundException,InvalidOperationException,ResourceDoesNotExistException {
        //check user credentials
        Customer customer = customerService.getCustomerByEmailId(emailId);
        Driver driver = driverService.getDriverByEmailId(emailId);

        String userType = "";
        AppUser user = null;
        Integer userId = -1;

        if(customer != null){
            userId = customer.getId();
            userType = "CUSTOMER";
            user = customer;
        }
        else if(driver != null){
            userId = driver.getId();
            userType = "DRIVER";
            user = driver;
        }
        else{
            throw new UserNotFoundException(String.format("User with id %d not exist",userId));
        }

        //Handle : if Booking is not available
        Booking booking = bookingRepository.findById(bookingId).orElse(null);

        if(booking == null){
            throw new ResourceDoesNotExistException(String.format("Booking with id %d does not exist in system",bookingId));
        }

        //if booking exist, this lines will run
        if(operation.equals("ACCEPT")){
            if(userType.equals("CUSTOMER")){
                throw new InvalidOperationException(String.format("Customer can not accept his own ride"));
            }
            //Driver has to accept booking //we fetch entire tuple which bookingId is matched
            booking.setDriver(driver);
            booking.setStatus("ACCEPTED"); // update and then save again in DB
            booking.setBillAmount(100);
            bookingRepository.save(booking);
            return String.format("Driver with id %d accepted booking with id % d",userId,bookingId);
        }
        else if(operation.equals("CANCEL")){
            if(userType.equals("CUSTOMER")){
                if(booking.getCustomer().getId() == userId){
                    booking.setStatus("CANCELLED");
                    bookingRepository.save(booking);
                    return String.format("Customer with id %d cancelled ride with booking id %d",userId,bookingId);
                }
                else{
                    throw new InvalidOperationException(String.format("Customer with id %d is not allowed to cancel booking with id %d",userId,bookingId));
                }
            }
            else if(userType.equals("DRIVER")){
                if(booking.getDriver().getId() == userId){
                    booking.setStatus("CANCELLED");
                    bookingRepository.save(booking);
                    return String.format("Driver with id %d cancelled booking with id %d", userId,bookingId);
                }
                else{
                    throw new InvalidOperationException(String.format("Driver with id %d is not allowed to cancel booking with id %d",userId,bookingId));
                }
            }
        }
        return "";
    }
}
