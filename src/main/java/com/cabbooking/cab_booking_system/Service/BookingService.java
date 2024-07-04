package com.cabbooking.cab_booking_system.Service;

import com.cabbooking.cab_booking_system.Exceptions.UserNotFoundException;
import com.cabbooking.cab_booking_system.Models.Booking;
import com.cabbooking.cab_booking_system.Models.Customer;
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
}
