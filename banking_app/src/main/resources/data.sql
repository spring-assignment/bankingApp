create table investment_plan(investment_id integer not null auto_increment,plan_name varchar(20),plan_description varchar(200));
insert into investment_plan(plan_name,plan_description) values('PLAN-A','Can Invest in real estate
Can invest in equity mutual funds
Sovereign Gold Bonds');
insert into investment_plan(plan_name,plan_description) values('PLAN-B','Fixed deposit can be made for 15 years and you get assured returns over time
Can invest in Senior Citizen Savings Scheme');

create table account(account_id integer not null auto_increment,account_balance bigint,account_number varchar(20),account_status varchar(30),account_type varchar(30),customer_name varchar(30),date_of_birth varchar(20),email_id varchar(20),pan_id varchar(20),phone_number varchar(20));
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(1, '10000', '2110440001', 'Active', 'saving', 'kumar', '1992-02-08', 'kumar567@gmail.com', 'BANF10NJKP', '8234567190');
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(2, '55000', '2110440002', 'Active', 'saving', 'Ram', '1980-05-13', 'ram1990@gmail.com', 'AAAA22KKLL', '9123457896');
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(3, '30030', '2110440003', 'Active', 'saving', 'Jhon', '1990-03-11', 'jhon23@gmail.com', 'PLQKJ7HJ8K', '8765678945');
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(4, '25000', '2110440004', 'Active', 'saving', 'Surya', '1969-07-05', 'surya54@gmail.com', 'NKOL8LPK9J', '7895637869');
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(5, '1024', '2110440005', 'Active', 'saving', 'Mohan', '1993-05-23', 'mohan123@gmail.com', 'QWE8ILOKBB', '9867856786');
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(6, '99', '2110440006', 'Active', 'saving', 'Tom', '1988-04-04', 'tom234@gmail.com', 'CBDJ3FD5GH', '7893465432');
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(7, '34567', '2110440007', 'Active', 'saving', 'Khan', '1991-05-28', 'khan100@gmail.com', 'PKFRU17APS', '8907657890');
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(8, '24600', '2110440008', 'Active', 'saving', 'Vivek', '1972-02-18', 'vivek111@gmail.com', 'BPCKU7JK8T', '9878986754');
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(9, '240000', '2110440009', 'Active', 'saving', 'Nirmal', '1987-08-03', 'niraml@gmail.com', 'ONFFGG2EF7', '9871234543');
insert into Account(account_id,account_balance,account_number,account_status,account_type,customer_name,date_of_birth,email_id,pan_id,phone_number) values(10, '803480', '2110440010', 'Active', 'saving', 'Tony', '1986-11-11', 'tony11@gmail.com', 'MMKKL2DG8H', '9444666897');

create table transaction(transaction_id integer not null auto_increment,account_number varchar(20),to_account_number varchar(20),transaction_amount float,transaction_date varchar(25));
insert into Transaction values(1, '2110440001', '2110440002', '1000', '2021-02-02');
insert into Transaction values(2, '2110440002', '2110440003', '5000', '2022-03-04');
insert into Transaction values(3, '2110440003', '2110440002', '20000', '2020-01-01');
insert into Transaction values(4, '2110440004', '2110440005', '3500', '2021-02-11');
insert into Transaction values(5, '2110440005', '2110440006', '149', '2022-01-11');
insert into Transaction values(6, '2110440006', '2110440004', '25', '2022-01-24');
insert into Transaction values(7, '2110440007', '2110440010', '2499', '2020-08-05');
insert into Transaction values(8, '2110440008', '2110440009', '11000', '2022-03-03');
insert into Transaction values(9, '2110440009', '2110440007', '100000', '2021-12-02');
insert into Transaction values(10, '2110440010', '2110440008', '55000', '2022-02-02');
