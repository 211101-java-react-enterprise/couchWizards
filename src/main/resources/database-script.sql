-- Table creation/reset for p1

drop table if exists cards;

/*	All cards have...
 * 		- card name
 *  	- print date
 * 		- supertype
 *  Cards can be reprinted so primary key is
 * 		- name, date
 *
 */
create table cards (
	card_name varchar not null,
	print_date date not null,
	c_cost varchar,
	supertype varchar not null,
	subtype varchar,
	c_power varchar(4),
	c_tough varchar(4),
	c_desc varchar,
	value numeric(11, 2),
	primary key (card_name, print_date)
);



-- Creates a searchable index so you can find cards by just print date
create unique index idx1 on cards (print_date, card_name);
