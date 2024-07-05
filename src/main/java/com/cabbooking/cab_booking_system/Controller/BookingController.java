package com.cabbooking.cab_booking_system.Controller;

import com.cabbooking.cab_booking_system.Exceptions.InvalidOperationException;
import com.cabbooking.cab_booking_system.Exceptions.ResourceDoesNotExistException;
import com.cabbooking.cab_booking_system.Exceptions.UserNotFoundException;
import com.cabbooking.cab_booking_system.Models.Booking;
import com.cabbooking.cab_booking_system.Repository.BookingRepository;
import com.cabbooking.cab_booking_system.RequestBody.CustomerBookingRequestBody;
import com.cabbooking.cab_booking_system.ResponseBody.BookingResponseBody;
import com.cabbooking.cab_booking_system.Service.BookingService;
import com.cabbooking.cab_booking_system.Service.CustomerService;
import com.cabbooking.cab_booking_system.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;


    @PostMapping("/request")
    public String createCustomerBooking(@RequestBody CustomerBookingRequestBody customerBookingRequestBody,
                                      @RequestParam int customerId){
        String startingLocation = customerBookingRequestBody.getStartingLocation();
        String endingLocation = customerBookingRequestBody.getEndingLocation();

        try {
            bookingService.handleCustomerRequest(startingLocation,endingLocation,customerId);
            return "Waiting for driver to accept";
        }catch(UserNotFoundException userNotFoundException){
            return userNotFoundException.getMessage();
        }
    }

    @GetMapping("/all")
    public List<BookingResponseBody> getBookingByStatus(@RequestParam String state){
        return bookingService.getBookingByStatus(state);
    }

    @PutMapping("/update")
    public ResponseEntity updateBookingStatus(@RequestParam String opr, @RequestParam String email, @RequestParam Integer bookingId){

        try{
            String response = bookingService.updateBooking(opr,email,bookingId);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch(UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(InvalidOperationException invalidOperationException){
            return new ResponseEntity(invalidOperationException.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
        }
        catch(ResourceDoesNotExistException resourceDoesNotExistException){
            return new ResponseEntity(resourceDoesNotExistException.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
