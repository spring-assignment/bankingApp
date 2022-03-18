# Bank Application
Using Banking application to tap affluent customers for investment opportunities			
			
			
	Functional Description	Technical details	
SYSTEM	Bank Application	Create APIs for fetching account details from database(DB) ; accountController for managing the account , may use methods : accountStatementRequest , TransferBalanceRequest , MonthlyAverageBalanceCheck , AccountStatement, response, account , errorHandling etc. Use Maven 3.2+ 	
Name	UC1 :  Create API to validate account balance of customers for investment email	REST API config : Usage of @Profile , @GetMapping , @PostMapping, @RequestMapping ; Usage of org.springframework.http.ResponseEntity for responses is advised	
Actors	1: Investment manager(IM)              2: Banking application	QC : JUnit and Integration scenarios.	
Description	The investment manager verifies accounts that are eligible to be suggested investment products and notifies customers 	Databases : Use Spring Data JPA with H2 for H2 database config. 	
As an investment manager for a bank ,  I want to be able to :	create a list of accounts with positive balance, 1000$+ MAB and valid contact info , then share Equity instrument brochures for investment on contact info	Logging : Logging of transactions, marketing emails, SMS confirmations using SLF4J(Log4J not recommended) 	
Main Scenario	1) The IM queries for bank accounts with non-zero , positive balance and retains data returned	Exception Handling : Usage of @RestControllerAdvice for eception handling with response body for REST services	
	2) The IM queries for the balance of the above accounts for the last three years	Service discovery :  Usage of Zuul and Eureka for Service discovery | Spring Boot Actuators for Health and monitoring	
	3) Filter the accounts with minimum 1000$ monthly average balance(MAB is already a field) and verify if they have email address and phone numbers		
	4) Trigger marketing mail for investment product on customer email address , send info update in SMS on phone number		
	"5) Validate account details fit for investment product on basis of  : 
a) Primary Key: Account ID 
b) Customer Name
c) Tax ID number (PAN) 
d) Date of Birth
e) Account Type
f) Account Status
g) Account Balance
h) Phone Number and email ID
i) Monthly Average Balance (MAB) 

are some of the mandatory information for account. Other parameters may be added as needed."	"VALIDATIONS : 
a) Account ID : Numeric 10 digit
b) Customer Name: No validation
c) Tax ID number (PAN) : Use microservice to verify [Needed at time of balance/amount transfers - not part of this UC but necessary microservice call] 
d) Date of Birth : MMDDYYYY format + Age is 18 <AGE <60 years [No Minor-Guardian accounts]
e) Account Type: Non-Salary + Non-Current | Savings
f) Account Status : Active 
g) Account Balance : Positive , non-zero balance accounts only
h) Phone Number : 10 numeric digits |  Email ID :  Not empty
i) Monthly Average Balance (MAB) : $1000 and above"	
Alternative A	If customer's balance is not validated as above, skip trigger of marketing mail, read next		
Alternative B	If customer's balance is validated, but MAB<1000$ , skip marketing mail trigger ,  read next		
Final state	Accounts with positive , non-zero balance and a MAB>=1000$ will be contacted on email and SMS for investment product		
Assumptions : 	1) API for ONLY balance check is available ; both or either phone number and email ID are accpted for success scenario		
	2) Environments and databases are distinct( eg. QA env for application has QA database)		
	3) Application is secure and logs are monitored		
![image](https://user-images.githubusercontent.com/100467612/158989482-d4657ed2-0ae1-43df-8781-a1cb83191902.png)

