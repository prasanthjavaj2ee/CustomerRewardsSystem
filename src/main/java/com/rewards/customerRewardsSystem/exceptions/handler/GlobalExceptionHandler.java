package com.rewards.customerRewardsSystem.exceptions.handler;

import com.rewards.customerRewardsSystem.exceptions.NoCustomerFoundException;
import com.rewards.customerRewardsSystem.exceptions.NoRewardsFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = NoCustomerFoundException.class)
    public ResponseEntity<Object> customerNotFoundException(NoCustomerFoundException noCustomerFoundException) {

        return buildResponseEntity(new CustomerRewardsApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND, "NO CUSTOMER FOUND WITH GIVEN ID", new ArrayList()));
    }

    @ExceptionHandler(value = {Exception.class, SQLException.class})
    public ResponseEntity<Object> serverConnectionFailsException(Exception exception) {
        return buildResponseEntity(new CustomerRewardsApiErrors(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", new ArrayList()));
    }
    @ExceptionHandler(value = NoRewardsFoundException.class)
    public ResponseEntity<Object> noRewardsFound(Exception exception) {
        return buildResponseEntity(new CustomerRewardsApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND, "NO REWARDS FOUND FOR GIVEN CUSTOMER", new ArrayList()));
    }
    public static ResponseEntity<Object> buildResponseEntity(CustomerRewardsApiErrors rewardsApiError) {
        return new ResponseEntity<>(rewardsApiError, rewardsApiError.getStatus());
    }
}
