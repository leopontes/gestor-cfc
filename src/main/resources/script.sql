
create table app_user(
	user_id            bigint       not null,
	user_name          varchar(80)  not null,
	encrypted_password varchar(200) not null,
	enabled            int          not null
);

alter table app_user add constraint app_user_pk primary key(user_id);
alter table app_user add constraint app_user_uk unique(user_name);

create table app_role(
	role_id   bigint      not null,
	role_name varchar(40) not null
);

alter table app_role add constraint app_role_pk primary key(role_id);
alter table app_role add constraint app_role_uk unique(role_name);

create table user_role(
	id      bigint not null,
	user_id bigint not null,
	role_id bigint not null
);

alter table user_role add constraint user_role_pk primary key(id);
alter table user_role add constraint user_role_uk unique(user_id, role_id);
alter table user_role add constraint user_role_fk_user foreign key(user_id) references app_user(user_id);
alter table user_role add constraint user_role_fk_role foreign key(role_id) references app_role(role_id);

create table persistent_logins(
	username varchar(80) not null,
	series   varchar(80) not null,
	token    varchar(80) not null,
	last_used timestamp  not null,
	primary key(series)
);


insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
---
 
insert into app_role (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');
 
insert into app_role (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_USER');
 
---
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (1, 1, 1);
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (2, 1, 2);
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (3, 2, 2);
---
commit;