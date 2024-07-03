package com.cabbooking.cab_booking_system.RequestBody;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserCredentialRequestBody {
    private String email;
    private String password;
}
