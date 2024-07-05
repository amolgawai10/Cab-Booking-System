package com.cabbooking.cab_booking_system.Exceptions;

public class ResourceDoesNotExistException extends RuntimeException{
    public ResourceDoesNotExistException(String msg){
        super(msg);
    }
}
