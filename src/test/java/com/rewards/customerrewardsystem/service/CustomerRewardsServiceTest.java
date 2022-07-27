package com.rewards.customerrewardsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rewards.customerrewardsystem.entyties.CustomerTransaction;
import com.rewards.customerrewardsystem.exceptions.NoCustomerFoundException;
import com.rewards.customerrewardsystem.exceptions.NoRewardsFoundException;
import com.rewards.customerrewardsystem.repository.CustomerTransactionRepository;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerRewardsService.class})
@ExtendWith(SpringExtension.class)
class CustomerRewardsServiceTest {
    @Autowired
    private CustomerRewardsService customerRewardsService;

    @MockBean
    private CustomerTransactionRepository customerTransactionRepository;


    @Test
    public void testGetCustomerRewardsById() throws NoCustomerFoundException, NoRewardsFoundException {
        when(customerTransactionRepository.findAllCustomerTxnByLast3months((Long) any())).thenReturn(new ArrayList<>());
        assertThrows(NoCustomerFoundException.class, () -> customerRewardsService.getCustomerRewardsById(123L));
        verify(customerTransactionRepository).findAllCustomerTxnByLast3months((Long) any());
    }


    @Test
   public void testGetCustomerRewardsById2() throws NoCustomerFoundException, NoRewardsFoundException {
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setCustTxnId(123L);
        customerTransaction.setCustomerId(123L);
        customerTransaction.setTxnAmount(10.0d);
        customerTransaction.setTxnDate(mock(Date.class));

        ArrayList<CustomerTransaction> customerTransactionList = new ArrayList<>();
        customerTransactionList.add(customerTransaction);
        when(customerTransactionRepository.findAllCustomerTxnByLast3months((Long) any()))
                .thenReturn(customerTransactionList);
        assertThrows(NoRewardsFoundException.class, () -> customerRewardsService.getCustomerRewardsById(123L));
        verify(customerTransactionRepository).findAllCustomerTxnByLast3months((Long) any());
    }


    @Test
    public void testGetCustomerRewardsById3() throws NoCustomerFoundException, NoRewardsFoundException {
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setCustTxnId(123L);
        customerTransaction.setCustomerId(123L);
        customerTransaction.setTxnAmount(10.0d);
        customerTransaction.setTxnDate(mock(Date.class));
        Date date = mock(Date.class);
        when(date.getMonth()).thenReturn(1);

        CustomerTransaction customerTransaction1 = new CustomerTransaction();
        customerTransaction1.setCustTxnId(123L);
        customerTransaction1.setCustomerId(123L);
        customerTransaction1.setTxnAmount(100.0d);
        customerTransaction1.setTxnDate(date);

        ArrayList<CustomerTransaction> customerTransactionList = new ArrayList<>();
        customerTransactionList.add(customerTransaction1);
        customerTransactionList.add(customerTransaction);
        when(customerTransactionRepository.findAllCustomerTxnByLast3months((Long) any()))
                .thenReturn(customerTransactionList);
        Map<String, Double> actualCustomerRewardsById = customerRewardsService.getCustomerRewardsById(123L);
        assertEquals(2, actualCustomerRewardsById.size());
        Double expectedGetResult = new Double(50.0d);
        assertEquals(expectedGetResult, actualCustomerRewardsById.get("TOTAL"));
        Double expectedGetResult1 = new Double(50.0d);
        assertEquals(expectedGetResult1, actualCustomerRewardsById.get("FEBRUARY"));
        verify(customerTransactionRepository).findAllCustomerTxnByLast3months((Long) any());
        verify(date).getMonth();
    }


    @Test
    public void testGetCustomerRewardsById4() throws NoCustomerFoundException, NoRewardsFoundException {
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setCustTxnId(123L);
        customerTransaction.setCustomerId(123L);
        customerTransaction.setTxnAmount(10.0d);
        customerTransaction.setTxnDate(mock(Date.class));
        Date date = mock(Date.class);
        when(date.getMonth()).thenReturn(1);

        CustomerTransaction customerTransaction1 = new CustomerTransaction();
        customerTransaction1.setCustTxnId(123L);
        customerTransaction1.setCustomerId(123L);
        customerTransaction1.setTxnAmount(100.0d);
        customerTransaction1.setTxnDate(date);
        Date date1 = mock(Date.class);
        when(date1.getMonth()).thenReturn(1);

        CustomerTransaction customerTransaction2 = new CustomerTransaction();
        customerTransaction2.setCustTxnId(123L);
        customerTransaction2.setCustomerId(123L);
        customerTransaction2.setTxnAmount(Double.NaN);
        customerTransaction2.setTxnDate(date1);

        ArrayList<CustomerTransaction> customerTransactionList = new ArrayList<>();
        customerTransactionList.add(customerTransaction2);
        customerTransactionList.add(customerTransaction1);
        customerTransactionList.add(customerTransaction);
        when(customerTransactionRepository.findAllCustomerTxnByLast3months((Long) any()))
                .thenReturn(customerTransactionList);
        Map<String, Double> actualCustomerRewardsById = customerRewardsService.getCustomerRewardsById(123L);
        assertEquals(2, actualCustomerRewardsById.size());
        Double expectedGetResult = new Double(50.0d);
        assertEquals(expectedGetResult, actualCustomerRewardsById.get("TOTAL"));
        Double expectedGetResult1 = new Double(50.0d);
        assertEquals(expectedGetResult1, actualCustomerRewardsById.get("FEBRUARY"));
        verify(customerTransactionRepository).findAllCustomerTxnByLast3months((Long) any());
        verify(date).getMonth();
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testGetCustomerRewardsById5() throws NoCustomerFoundException, NoRewardsFoundException {


        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setCustTxnId(123L);
        customerTransaction.setCustomerId(123L);
        customerTransaction.setTxnAmount(10.0d);
        customerTransaction.setTxnDate(mock(Date.class));
        Date date = mock(Date.class);
        when(date.getMonth()).thenReturn(1);

        CustomerTransaction customerTransaction1 = new CustomerTransaction();
        customerTransaction1.setCustTxnId(123L);
        customerTransaction1.setCustomerId(123L);
        customerTransaction1.setTxnAmount(100.0d);
        customerTransaction1.setTxnDate(date);
        Date date1 = mock(Date.class);
        when(date1.getMonth()).thenReturn(-1);

        CustomerTransaction customerTransaction2 = new CustomerTransaction();
        customerTransaction2.setCustTxnId(123L);
        customerTransaction2.setCustomerId(123L);
        customerTransaction2.setTxnAmount(100.0d);
        customerTransaction2.setTxnDate(date1);

        ArrayList<CustomerTransaction> customerTransactionList = new ArrayList<>();
        customerTransactionList.add(customerTransaction2);
        customerTransactionList.add(customerTransaction1);
        customerTransactionList.add(customerTransaction);
        when(customerTransactionRepository.findAllCustomerTxnByLast3months((Long) any()))
                .thenReturn(customerTransactionList);
        customerRewardsService.getCustomerRewardsById(123L);
    }


    @Test
   public void testGetCustomerRewardsById6() throws NoCustomerFoundException, NoRewardsFoundException {
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setCustTxnId(123L);
        customerTransaction.setCustomerId(123L);
        customerTransaction.setTxnAmount(10.0d);
        customerTransaction.setTxnDate(mock(Date.class));
        Date date = mock(Date.class);
        when(date.getMonth()).thenReturn(1);

        CustomerTransaction customerTransaction1 = new CustomerTransaction();
        customerTransaction1.setCustTxnId(123L);
        customerTransaction1.setCustomerId(123L);
        customerTransaction1.setTxnAmount(100.0d);
        customerTransaction1.setTxnDate(date);
        Date date1 = mock(Date.class);
        when(date1.getMonth()).thenReturn(0);

        CustomerTransaction customerTransaction2 = new CustomerTransaction();
        customerTransaction2.setCustTxnId(123L);
        customerTransaction2.setCustomerId(123L);
        customerTransaction2.setTxnAmount(100.0d);
        customerTransaction2.setTxnDate(date1);

        ArrayList<CustomerTransaction> customerTransactionList = new ArrayList<>();
        customerTransactionList.add(customerTransaction2);
        customerTransactionList.add(customerTransaction1);
        customerTransactionList.add(customerTransaction);
        when(customerTransactionRepository.findAllCustomerTxnByLast3months((Long) any()))
                .thenReturn(customerTransactionList);
        Map<String, Double> actualCustomerRewardsById = customerRewardsService.getCustomerRewardsById(123L);
        assertEquals(3, actualCustomerRewardsById.size());
        Double expectedGetResult = new Double(50.0d);
        assertEquals(expectedGetResult, actualCustomerRewardsById.get("JANUARY"));
        Double expectedGetResult1 = new Double(100.0d);
        assertEquals(expectedGetResult1, actualCustomerRewardsById.get("TOTAL"));
        Double expectedGetResult2 = new Double(50.0d);
        assertEquals(expectedGetResult2, actualCustomerRewardsById.get("FEBRUARY"));
        verify(customerTransactionRepository).findAllCustomerTxnByLast3months((Long) any());
        verify(date1).getMonth();
        verify(date).getMonth();
    }
}

