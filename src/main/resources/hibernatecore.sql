CREATE TABLE `user`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `fullname`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `username`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `password`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `created_at`  datetime                                DEFAULT NULL ON UPDATE current_timestamp(),
    `modified_at` datetime                                DEFAULT NULL ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


create table dataset
(
    id             bigint primary key auto_increment,
    name           varchar(255) charset utf8mb3 unique,
    fields_list    varchar(255),
    is_range_input bit,
    created_at     timestamp default current_timestamp() not null,
    created_by     varchar(255),
    updated_at     timestamp default current_timestamp() not null,
    updated_by     varchar(255)
);

create table dataset_value
(
    id         bigint primary key auto_increment,
    dataset_id bigint,
    value      varchar(255),
    created_at timestamp default current_timestamp() not null,
    created_by varchar(255),
    updated_at timestamp default current_timestamp() not null,
    updated_by varchar(255),
    constraint dataset_value_dataset_id_fk foreign key (dataset_id) references dataset (id)
)
