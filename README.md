# CustomerRewardsSystem
CustomerRewardsSystem calculates the rewards for the customer transactions by month wise and total


## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle 7.5](https://gradle.org/next-steps/?version=7.5&format=bin)

## Project Dependencies
- [Spring-Boot 2.7.1]

## Running the application locally
There are several ways to run a CustomerRewardsSystem application on your local machine. One way is to execute the `main` method in the `com.rewards.customerRewardsSystem.CustomerRewardsSystemApplication` class from your IDE.

Alternatively you can use the [Spring Boot Gradle plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins.gradle) like so:

```shell
gradle clean
gradle bootRun
```
This will create:

* H2 in memory data base
* CustomerRewardsSystem application creates table(CUSTOMER_TRANSACTION) mentioned in schema.sql
* Insert the test data provided in data.sql while application bootstrap
* URL to acces the H2 console [H2-console](http://localhost:8080/Customer/h2-ui/)
* Provide JDBC URL 'jdbc:h2:mem:testdb' in h2 console and connect DB to verify the test data
* ![MY_image](https://www.codejava.net/images/articles/frameworks/springboot/h2-connect/H2_console_localhost.png)

* Below are the Rest Services URL to test CustomerRewardsSystem.
```shell
http://localhost:8080/Customer/api/getCustomerById/1

```
If Customer Txn amount only 120 As per Requirement Document
Response:
{
    "JULY": 90.0,
    "TOTAL": 90.0
}
```shell
http://localhost:8080/Customer/api/getCustomerById/123

```
Response:
{
    "JUNE": 49.989999999999995,
    "MAY": 295.65,
    "JULY": 475.0,
    "TOTAL": 820.64
}
```shell
http://localhost:8080/Customer/api/getCustomerById/1234
```
IF All transction For the Customer ID:1234 is less than are equal 50$
application throws the NoRewardsFoundException 
Global Exception Handler will returns below reponse.
Response:{
    "timestamp": "23-07-2022 02:00:06",
    "status": "NOT_FOUND",
    "message": "NO REWARDS FOUND FOR GIVEN CUSTOMER",
    "errors": []
}
```shell
http://localhost:8080/Customer/api/getCustomerById/1111
```
If No Customer Found with given ID,application throws the NoCustomerFoundException
and Global Exception Handler will returns below reponse.

Response:
{
    "timestamp": "23-07-2022 02:02:18",
    "status": "NOT_FOUND",
    "message": "NO CUSTOMER FOUND WITH GIVEN ID",
    "errors": []
}
