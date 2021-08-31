CREATE TABLE IF NOT EXISTS stocks(
id int auto_increment primary key,
stockTicker varchar(10) not null,
price double not null,
volume int not null,
buyOrSell varchar(5) not null,
--orderTime datetime default current_timestamp on update current_timestamp,
statusCode int default 0
);


INSERT INTO stocks(stockTicker, price, volume, buyOrSell, statusCode) VALUES ('TATA', 800, 100, 'buy', 0);