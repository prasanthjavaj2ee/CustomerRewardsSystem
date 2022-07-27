package com.rewards.customerrewardsystem.repository;

import com.rewards.customerrewardsystem.entyties.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {

    @Query(value = "SELECT * FROM CUSTOMER_TRANSACTION WHERE txndate >= DATEADD(DAY, -90, NOW()) AND customerid=:customerId", nativeQuery = true)
    List<CustomerTransaction> findAllCustomerTxnByLast3months(@Param("customerId") Long customerId);
}
