insert into business(
business_name,
address,
zipcode,
email,
telephone,
opening_hours,
text_description
)
VALUES(
'testsenter',
'adressetest',
7227,
'asdf@asdf.fre',
73737373,
'08200915',
'description description description description description description description description description description description description description description description description description description description description '
);

insert into shoppingcentre(business_id, parking_spaces) values(1,1);

insert into building(
centre_id,
building_name,
floors,
area)
values(1,'anotherbuilding',12,3645);
insert into building(
centre_id,
building_name,
floors,
area)
values(1,'andathird',2,768549);
insert into building(
centre_id,
building_name,
floors,
area)
values(1,'testbuilding',20,20000);


insert into business(business_name, address, zipcode, email, telephone, opening_hours, text_description)
values('shop1','road 1', 7227, 'asdf@fds.fd', 27364517, '09150915', 'this be descript');

insert into establishment(business_id, building_id, floor_number)
values(2, 1, 2);



insert into business(business_name, address, zipcode, email, telephone, opening_hours, text_description)
values('shop1','road 1', 7227, 'asdf@fds.fd', 27364517, '09150915', 'this be descript');

insert into establishment(business_id, building_id, floor_number)
values(3,2,1);



insert into business(business_name, address, zipcode, email, telephone, opening_hours, text_description)
values('shop1','road 1', 7227, 'asdf@fds.fd', 27364517, '09150915', 'this be descript');

insert into establishment(business_id, building_id, floor_number)
values(4,3,2);


insert into trade(trade_name)
values('shoes');

insert into trade(trade_name)
values('food');

insert into trade(trade_name)
values('electronics');

insert into trade(trade_name)
values('toys');

insert into trade(trade_name)
values('clothing');

insert into trade(trade_name)
values('spirits');

insert into trade(trade_name)
values('phones');

insert into trade(trade_name)
values('travel');

insert into trade(trade_name)
values('internet cafe');

insert into trade(trade_name)
values('this trade has a long name');

insert into establishmenttrade(establishment_id, trade_id)
values(1,1);

insert into establishmenttrade(establishment_id, trade_id)
values(1,2);

insert into establishmenttrade(establishment_id, trade_id)
values(2,3);

insert into establishmenttrade(establishment_id, trade_id)
values(2,4);

insert into establishmenttrade(establishment_id, trade_id)
values(3, 5);

insert into establishmenttrade(establishment_id, trade_id)
values(3, 6);

insert into person(first_name,last_name, address,zipcode,email,telephone,salary
)
values('firstName','lastName','Gate 1',7227,'',93065598,100000),
('anothername','gundersen','Street 1',1337,'',33055597,102000),
('asdf','few','fewww 1',0001,'',33445566,122000);

select trade_id, trade_name 
from establishmenttrade 
left join trade 
using(trade_id) 
where establishment_id = 3;


select * from establishment_view;

select * from establishment;

SHOW TABLE STATUS WHERE Name = 'establishment';

