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
	print_set varchar not null,
	c_cost varchar,
	supertype varchar not null,
	subtype varchar,
	c_power varchar(4),
	c_tough varchar(4),
	c_desc varchar,
	value numeric(11, 2),
	card_id varchar not null,
	primary key (card_id)
);



-- Creates a searchable index so you can find cards by just print date
create unique index idx1 on cards (print_set, card_name);

-- Creates a dummy card for testing purposes
insert into cards (card_name, print_set, c_cost, supertype, subtype, c_power, c_tough, c_desc, value, card_id) values
('Black Lotus', 'Limited Edition Alpha', 'Q', 'Artifact', null, null, null, 'Add 3 mana of any single color of your choice to your mana pool, then is discarded. Tapping this artifact can be played as an interrupt', 3149.68, 'd2ad27f2-650f-48f3-ab8c-81b6ae473a1c');


-- Example query for anything with a total cost of 1 mana
select * from cards where length(c_cost) = 1 ;
-- TODO figure out a query to find anything that costs 1 mana of a specific color.
