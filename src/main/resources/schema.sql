CREATE TABLE IF NOT EXISTS stocks (
id int auto_increment primary key,
stockTicker varchar(10) not null,
price double not null,
volume int not null,
buyOrsell varchar(255) not null,
statusCode int default 0
);

insert into stocks(stockTicker,price,volume,buyOrsell) values('ITC', 10, 50, 'buy');
insert into stocks(stockTicker,price,volume,buyOrsell,statusCode) values('ZOMATO', 100, 50, 'sell', 0);
insert into stocks(stockTicker,price,volume,buyOrsell,statusCode) values('ITC', 10, 25, 'sell', 0);
insert into stocks(stockTicker,price,volume,buyOrsell) values('ZOMATO', 100, 20, 'buy');