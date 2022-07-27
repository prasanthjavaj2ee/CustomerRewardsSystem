package com.rewards.customerrewardsystem.api;

import com.rewards.customerrewardsystem.exceptions.NoCustomerFoundException;
import com.rewards.customerrewardsystem.exceptions.NoRewardsFoundException;
import com.rewards.customerrewardsystem.service.CustomerRewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerRewardsController {
    @Autowired
    CustomerRewardsService customerRewardsService;

    /**
     * @param id
     * @return Map<String, Double>
     * @throws NoCustomerFoundException
     * @throws NoRewardsFoundException
     */
    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Map<String, Double>> getCustomerRewardsById(@PathVariable("id") Long id) throws NoCustomerFoundException, NoRewardsFoundException {
        return new ResponseEntity<>(customerRewardsService.getCustomerRewardsById(id), HttpStatus.OK);
    }

}
