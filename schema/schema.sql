CREATE Database ticker;
use ticker;
create table if not exists stocks(
id int auto_increment primary key,
stockTicker varchar(10) not null,
price double not null,
volume int not null,
buyOrsell varchar(5) not null,
orderTime datetime default current_timestamp not null,
statusCode int default 0
);

insert into stocks(id, stockTicker, price, volume, buyOrsell, statusCode) values (1,"TATA", 800, 100, "buy", 0);