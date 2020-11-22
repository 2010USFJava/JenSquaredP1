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
eid serial not null,
event_id serial not null unique,
submission_date date not null,
event_date date not null,
event_location varchar not null,
event_type varchar not null,
event_description varchar not null,
grade_format varchar not null,
current_grade varchar not null,
form_status varchar not null,
reimbursement_amount numeric not null,
amount_status varchar not null,
urgent boolean not null,
supervisor_approval boolean not null,
department_head_approval boolean not null,
benefit_co_approval boolean not null,
approval_reponse varchar not null,
denial_response varchar not null,
file_attachment boolean,
foreign key (eid) references employee on delete cascade
);


select * from employee;
select * from form;

