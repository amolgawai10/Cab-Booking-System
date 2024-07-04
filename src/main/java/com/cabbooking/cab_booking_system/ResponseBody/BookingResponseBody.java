package com.cabbooking.cab_booking_system.ResponseBody;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingResponseBody {
    private int bookingId;
    private int customerId;
    private String customerName;
    private String startingLocation;
    private String endingLocation;
    private int billAmount;
    private String status;
}
