/*
    establishing new environment for database and tables
*/
drop database poised_pms_db;

create database if not exists poised_pms_db;
use poised_pms_db;

/*
    establishing new contacts table
    
    * contact_id is primary key
    * also adding auto increment contraint for primary key
*/
drop table if exists contacts;

create table contacts (
    contact_id int not null auto_increment,
    f_name varchar(25) not null,
    s_name varchar(25) not null,
    e_mail varchar(50),
    ph_num varchar(25),
    address varchar(100),
    poised_employee boolean not null,
    
    primary key (contact_id)
);

/*
    Add records to my table
*/
insert into contacts values (null, 'Italo', 'Calvino', 'i.calvino@gmail.com', '0019231985', 'Modena, Italy', false);
insert into contacts values (null, 'Samuel', 'Beckett', 'sb.dubs@yahoo.com', '0019061989', 'Paris, Frnace', true);
insert into contacts values (null, 'Umberto', 'Eco', 'eco.umberto@milan.edu', '0019322016', 'Milan, Italy', true);
insert into contacts values (null, 'Kurt', 'Vonnegut', 'b.pilgrim@gmail.com', '0019222007', 'Manhattan, New York', false);
insert into contacts values (null, 'James', 'Ballard', 'jgb@gmail.com', '0019302009', 'Shepperton, UK', true);
insert into contacts values (null, 'Donald', 'Barthelme', 'joker_king@gmail.com', '0019311989', 'Austim, Texas', true);
insert into contacts values (null, 'Vladimir', 'Nabokov', 'pnin@outlook.com', '0018991977', 'Geneve, Switzerland', false);
insert into contacts values (null, 'Roland', 'Barthes', 'rbarthes@hotmail.com', '0019151980', 'Algiers, Algeria', false);

/*
    establishing new project table
    
    * also adding auto increment contraint for primary key
    * project_id is primary key
    * project project_finalised is default false
*/
drop table if exists projects;

create table projects (
    project_id int not null auto_increment,
    project_name varchar(50),
    building_type varchar(25),
    building_address varchar(100),
    erf_number int,
    expected_fee double,
    fee_paid double,
    project_deadline date,
    project_finalised boolean not null,
    finalisation_date date, 
    engineer_id int, 
    architect_id int, 
    contractor_id int, 
    customer_id int,
    
    primary key (project_id),
    foreign key (engineer_id) references contacts (contact_id),
    foreign key (architect_id) references contacts (contact_id),
    foreign key (contractor_id) references contacts (contact_id),
    foreign key (customer_id) references contacts (contact_id)
);

/*
    Add records to my tables
*/
insert into projects values (null, 'House Eco', 'House', 'Rimini, Italy', 74845, 10000000.00, 0, '2022-10-19', false, '2022-01-05', null, null, null, null);
insert into projects values (null, 'House Nabokov', 'House', 'Lake Como, Italy', 97625, 10000000.00, 0, '2022-10-19', false, '2022-10-29', null, null, null, null);
/*
    Show off my new creation
    * list tables
    * show schema for tables
    * query tables
*/
show tables;
describe projects;
describe contacts;
select * from projects;
select * from contacts;
