create schema if not exists jensquared authorization jenny77;
set search_path to jensquared;

grant all privileges on schema jensquared to "JHeermance";


--create tables
create table employee (
eid serial primary key,
"name" varchar not null,
address varchar,
phone varchar not null,
email varchar not null,
username varchar not null unique,
"password" varchar not null,
available_reimbursement numeric not null,
pending_reimbursement numeric not null,
awarded_reimbursement numeric not null,
supervisor_id numeric not null,
department_head_id numeric not null,
benefit_co_id numeric not null,
is_supervisor boolean not null,
is_department_head boolean not null,
is_benefit_co boolean not null
);

create table form(
eid int not null,
event_id serial not null unique,
submission_date date not null,
event_type varchar not null,
event_name varchar not null,
event_description varchar not null,
event_date date not null,
event_time varchar not null,
time_missed numeric not null,
event_location varchar not null,
event_cost varchar not null,
grade_format varchar not null,
current_grade varchar not null,
reimbursement_amount numeric not null,
pre_approval boolean not null,
urgent boolean not null,
form_status varchar not null,
file_attachment boolean,
supervisor_approval boolean not null,
department_head_approval boolean not null,
benefit_co_approval boolean not null,
approval_reponse varchar,
denial_response varchar,
foreign key (eid) references employee(eid) on delete cascade
);

create table attachments (
event_id int not null,
file_name varchar(50),
file_attachment bytea not null,
foreign key (event_id) references form(event_id) on delete cascade
);

select * from employee;
select * from form;

drop table attachments;
drop table form;

insert into employee values (0,'jennifer hunter', '384 west st', '273-384-9302', 'jhunter@revature.net', 'jenny', 'fromtheblock', 1000, 500, 0, 39, 30, 50, false, false, false);
insert into employee values (default,'Jennifer Heermance', '17 Cherry Tree Lane', '309-867-5309', 'jheermance@revature.net', 'JHeermance', 'Heermancer1', 1000, 0, 0, 39, 30, 50, false, false, false);
insert into employee values (39,'Matt Knighten', '4202 E Fowler Ave', '813-266-2437', 'mknighten@revature.net', 'KittenKillzU', 'GitGudScrub', 1000, 0, 0, 30, 30, 50, true, false, false);
insert into employee values (30,'Jonathan Gworek', '11730 Plaza America Dr.', '703-570-8181', 'jgworek@revature.net', 'QCDude', 'LookIntoThat', 1000, 0, 0, 50, 30, 50, true, true, false);
insert into employee values (50,'POTUS', '1600 Pennsylvania Avenue NW', '202-456-1111', 'potus@revature.net', 'POTUS', 'TopDawg', 1000, 0, 0, 50, 50, 50, false, false, true);