package com.rewards.customerrewardsystem.exceptions;

public class NoCustomerFoundException extends Exception{
    public NoCustomerFoundException(){
        super("NO CUSTOMER FOUND WITH GIVEN ID");
    }
}

