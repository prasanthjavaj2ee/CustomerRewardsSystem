package com.rewards.customerRewardsSystem;

import com.rewards.customerRewardsSystem.api.CustomerRewardsController;
import com.rewards.customerRewardsSystem.exceptions.NoCustomerFoundException;
import com.rewards.customerRewardsSystem.exceptions.NoRewardsFoundException;
import com.rewards.customerRewardsSystem.repository.CustomerTransactionRepository;
import com.rewards.customerRewardsSystem.service.CustomerRewardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
class CustomerRewardsSystemApplicationTests {
    private MockMvc mockMvc;

    @Mock
    CustomerTransactionRepository customerTransactionRepository;
    @Mock
    CustomerRewardsService customerRewardsService;

    @InjectMocks
    CustomerRewardsController controller;
    Map<String, Double> custRewardsMapForTxnBelow$50 = new HashMap<>();
    Map<String, Double> custRewardsMapForTxnGt$50AndGt100 = new HashMap<>();
    Map<String, Double> custRewardsMapForTxnGt100 = new HashMap<>();

    @BeforeEach
    public void init() {
        custRewardsMapForTxnGt$50AndGt100.put("JUNE", 49.989999999999995d);
        custRewardsMapForTxnGt$50AndGt100.put("MAY", 295.65d);
        custRewardsMapForTxnGt$50AndGt100.put("JULY", 565.0d);
        custRewardsMapForTxnGt$50AndGt100.put("TOTAL", 910.64d);
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void getCustRewardsForTxnGt$50AndGt100() throws Exception {
        when(customerRewardsService.getCustomerRewardsById(1234L)).thenReturn(custRewardsMapForTxnGt$50AndGt100);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/getCustomerById/1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.MAY", is(295.65d)))
                .andExpect(jsonPath("$.JUNE", is(49.989999999999995)))
                .andExpect(jsonPath("$.JULY", is(565.0)))
                .andExpect(jsonPath("$.TOTAL", is(910.64)));
    }

    @Test
    public void custRewardsMapForTxnBelow$50()  {
        try {
            when(customerRewardsService.getCustomerRewardsById(1234L)).thenThrow(NoRewardsFoundException.class);
        } catch (NoCustomerFoundException e) {
           assertTrue(e instanceof NoCustomerFoundException);
        } catch (NoRewardsFoundException e) {
            assertTrue(e instanceof NoRewardsFoundException);
        }


    }

}
