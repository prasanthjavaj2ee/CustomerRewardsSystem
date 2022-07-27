package com.rewards.customerrewardsystem.entyties;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "customer_transaction")
public class CustomerTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUSTTXNID")
    private Long custTxnId;
    @Column(name = "CUSTOMERID")
    private Long customerId;
    @Column(name = "TXNAMOUNT")
    private Double txnAmount;
    @Column(name = "TXNDATE")
    private Date txnDate;



}
