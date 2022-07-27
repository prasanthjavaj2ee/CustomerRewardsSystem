package com.rewards.customerrewardsystem.exceptions.handler;

import com.rewards.customerrewardsystem.exceptions.NoCustomerFoundException;
import com.rewards.customerrewardsystem.exceptions.NoRewardsFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = NoCustomerFoundException.class)
    public ResponseEntity<Object> customerNotFoundException(NoCustomerFoundException noCustomerFoundException) {
        LOGGER.error("NoCustomerFoundException occurred, which will cause a {} response", HttpStatus.NOT_FOUND, noCustomerFoundException);
        return buildResponseEntity(new CustomerRewardsApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND, "NO CUSTOMER FOUND WITH GIVEN ID", new ArrayList()));
    }

    @ExceptionHandler(value = {Exception.class, SQLException.class})
    public ResponseEntity<Object> serverConnectionFailsException(Exception exception) {
        LOGGER.error("Exception occurred, which will cause a {} response", HttpStatus.INTERNAL_SERVER_ERROR, exception);
        return buildResponseEntity(new CustomerRewardsApiErrors(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", new ArrayList()));
    }
    @ExceptionHandler(value = NoRewardsFoundException.class)
    public ResponseEntity<Object> noRewardsFound(Exception exception) {
        LOGGER.error("NoRewardsFoundException occurred, which will cause a {} response", HttpStatus.INTERNAL_SERVER_ERROR, exception);
        return buildResponseEntity(new CustomerRewardsApiErrors(LocalDateTime.now(), HttpStatus.NOT_FOUND, "NO REWARDS FOUND FOR GIVEN CUSTOMER", new ArrayList()));
    }
    public static ResponseEntity<Object> buildResponseEntity(CustomerRewardsApiErrors rewardsApiError) {
        return new ResponseEntity<>(rewardsApiError, rewardsApiError.getStatus());
    }
}
