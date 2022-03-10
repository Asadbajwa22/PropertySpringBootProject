drop table if exists property CASCADE;
create table property (id integer AUTO_INCREMENT, bedrooms integer, price integer, type varchar(255), primary key (id));
