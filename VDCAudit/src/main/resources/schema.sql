create schema if not exists `audit`;
set schema `audit`;

create table `audit`.`customer_behavior`
(
    `id`              varchar(32) not null,
    `created_on`      datetime,
    `version`         int(11),
    `customer_id`     varchar(32),
    `behavior_type`   varchar(16),
    `behavior_detail` varchar(128),
    primary key (`id`)
);

create table `audit`.`product_price_update`
(
    `id`            varchar(32) not null,
    `created_on`    datetime,
    `version`       int(11),
    `product_id`    varchar(32),
    `current_price` bigint(20),
    `next_price`    bigint(20),
    primary key (`id`)
);

insert into `audit`.`customer_behavior` values ('koko123', curdate(), 0, 'aaaaa111111', 'VIEW', 'aa11aa');
insert into `audit`.`product_price_update` values ( 'h7t4g23', curdate(), 0, 'aa11aa', 900000, 2457000);