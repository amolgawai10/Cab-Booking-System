package com.cabbooking.cab_booking_system.RequestBody;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerBookingRequestBody {

    private String startingLocation;
    private String endingLocation;
}
