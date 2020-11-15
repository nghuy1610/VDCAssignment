create schema if not exists `product`;
set schema `product`;

create table `product`.`brand` (
    `id`         varchar(32) not null,
    `created_on` datetime,
    `version`    int(11),
    `name` nvarchar(128),
    primary key (`id`)
);

create table `product`.`product`
(
    `id`         varchar(32) not null,
    `created_on` datetime,
    `version`    int(11),
    `name`       nvarchar(128),
    `category`   nvarchar(128),
    `price`      bigint(20),
    `brand_id`   varchar(32),
    primary key (`id`),
    constraint fk_product_to_brand foreign key (`brand_id`) references `brand`(`id`)
);

create table `product`.`string_attribute`
(
    `id`         varchar(32) not null,
    `created_on` datetime,
    `version`    int(11),
    `name`       nvarchar(128),
    `value`      nvarchar(128),
    `product_id` varchar(32),
    primary key (`id`),
    constraint fk_string_attribute_to_product foreign key (`product_id`) references `product` (`id`)
);

create table `product`.`int_attribute`
(
    `id`         varchar(32) not null,
    `created_on` datetime,
    `version`    int(11),
    `name`       nvarchar(128),
    `value`      bigint(20),
    `product_id` varchar(32),
    primary key (`id`),
    constraint fk_int_attribute_to_product foreign key (`product_id`) references `product` (`id`)
);

create table `product`.`double_attribute`
(
    `id`         varchar(32) not null,
    `created_on` datetime,
    `version`    int(11),
    `name`       nvarchar(128),
    `value`      double(5),
    `product_id` varchar(32),
    primary key (`id`),
    constraint fk_double_attribute_to_product foreign key (`product_id`) references `product` (`id`)
);

insert into `product`.`brand` values ('99bb99', curdate(), 0, 'Lenovo');
insert into `product`.`product` values ('aa11aa', curdate(), 0, 'test product', 'LAPTOP', 12000000, '99bb99');
insert into `product`.`int_attribute` values ('hhjjll', curdate(), 0, 'length', 223, 'aa11aa');