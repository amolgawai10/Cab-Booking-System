package com.cabbooking.cab_booking_system.Exceptions;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String msg){
        super(msg);
    }
}
