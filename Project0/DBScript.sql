
set schema 'project0';

drop table customer;
drop table employee;
drop table account;

create table account( 
	main_id serial primary key,
	pw varchar not null,
	fName text not null,
	lName text not null,
	accType char check (accType in('E', 'C'))
);

select * from account;

commit;

create table customer( 
	custId serial primary key,
	main_id int unique references account(main_id),
	balance  numeric(10,2) not null,
	status char check (status in('A', 'I')),
	approval text check (approval in('Approved', 'New'))
);

select * from customer;

commit;


create table employee( 
	empId serial primary key,
	main_id int unique references account(main_id)
);

select * from project0.employee;

commit;
--delete  from account where main_id=1;

insert into account (pw, fName, lName, accType)
	   		values  ('1', 'Jain', 'Kim', 'C'),
			   		('2', 'Ryan', 'Hong', 'C'),
	   				('3', 'Sue', 'shin', 'C'),
	   				('1', 'EmpA', 'AAA', 'E'),
	   				('2', 'EmpB', 'BBB', 'E'),
	   				('3', 'EmpC', 'CCC', 'E'),
	   				('4', 'EmpD', 'DDD', 'E'),
	   				('5', 'EmpE', 'EEE', 'E');
	   				--returning "main_id";
	   				
insert into account (pw, fName, lName, accType)
	   		values  ('4', 'Cust4', '444', 'C'),
	   		  		('5', 'Cust5', '555', 'C'),
	   		  		('6', 'Cust6', '666', 'C');
	   		
	  


insert into customer (main_id, balance, status, approval)
	   		values  ('1', 1500.00, 'I', 'New'),
			   		('2', 1000.00, 'I', 'New'),
			   		('3', 112000.00, 'A', 'Approved'),
	   				('9', 1500.00, 'I', 'Approved'),
	   				('10', 3000.00, 'A', 'Approved'),
	   				('11', 50.00, 'I', 'Approved')
	   				

select * from project0.customer;

--delete  from customer where main_id>4 and main_id<10;

insert into employee (main_id)
	   		values	('4'),
			   		('5'),
			   		('6'),
	   				('7'),
	   				('8');
	   				
delete from customer where main_id = 18;
delete from account where main_id = 18;	   				
delete from customer where main_id = 17;
delete from account where main_id = 17;	   	
delete from customer where main_id = 16;
delete from account where main_id = 16;	   	
delete from customer where main_id = 15;
delete from account where main_id = 15;	   	
delete from customer where main_id = 14;
delete from account where main_id = 14;	   	
delete from customer where main_id = 13;
delete from account where main_id = 13;	   	
delete from customer where main_id = 12;
delete from account where main_id = 12;	   	
   	
select * from project0.account;
select * from project0.customer;
select * from project0.employee;


update project0.customer 
set status = 'I', approval = 'New'
where main_id=9;

select * from project0.account where acctype='C';

set schema 'project0';
select a.main_id, a.pw, a.fname, a.lname, c.balance, c.status, c.approval
from account a
join customer c 
on a.main_id = c.main_id 
where a.acctype = 'C' ;

and  c.status ='I' and c.approval='New';

select a.main_id, a.pw, a.fname, a.lname, c.balance, c.status 
from account a
join customer c 
on a.main_id = c.main_id 
where a.main_id = '10' and  a.acctype ='C';


select main_id, pw, fname, lname, acctype
from account
where main_id = 2 and acctype='C';

select status from customer where main_id =1;

select approval from project0.customer
where main_id = 2;

select * from project0.account;

update project0.account 
set fname = 'Lily', lname ='Morgan', pw='1111'
where main_id=11;

select * from project0.customer;


update project0.customer 
set approval = 'New', status = 'I'
where main_id=3;

set schema 'project0';
--delete from project0.customer where main_id=36;

update project0.customer 
set approval = 'New', status = 'I'
where main_id=11 or main_id=10 or main_id=35;

select * from project0.customer 
order by main_id;
