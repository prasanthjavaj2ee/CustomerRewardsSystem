CREATE TABLE customer_transaction(
custTxnId BIGINT AUTO_INCREMENT PRIMARY KEY,
customerId BIGINT NOT NULL,
txnAmount DOUBLE NOT NULL,
txnDate DATE
);