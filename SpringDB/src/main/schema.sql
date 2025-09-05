drop table member if exists cascade;
create table member (
                        member_id varchar(10),
                        money integer not null default 0,
                        primary key (member_id)
);

-- auto commit: set autocommit true;
set autocommit true;
insert into member(member_id, money) values ('data1',10000);
insert into member(member_id, money) values ('data2',10000);

-- manual commit: set autocommit false;
set autocommit false;
insert into member(member_id, money) values ('data3',10000);
insert into member(member_id, money) values ('data4',10000);
commit;