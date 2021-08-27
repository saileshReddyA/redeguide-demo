SET NAMES utf8;
SET
    time_zone = '+00:00';
SET
    sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

--
-- Create database if needed, from config property -- flyway.placeholders.schema
--

CREATE
    DATABASE IF NOT EXISTS `${schema}` DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;

USE
`${schema}`;

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization`
(
    `id`                 bigint    NOT NULL auto_increment,
    `external_id`        char(36)    NOT NULL,
    `name`         varchar(50) NULL,
    `description`          varchar(50) NOT NULL,
    `domain` varchar(50) NOT NULL,
    `enabled`            boolean     NOT NULL default true,
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `org_external_id_unique` (`external_id`),
    UNIQUE KEY `org_name_unique` (`name`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
    
    
DROP TABLE IF EXISTS `demo_sql`;

CREATE TABLE `demo_sql`
(
    `next_val`               bigint     NULL 
    
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`                 varchar(10)   NOT NULL ,
    `external_id`        char(36)    NULL,
    `first_name`         varchar(50) NOT NULL,
    `last_name`          varchar(50) NOT NULL,
    `email`              varchar(50) NOT NULL,
    `phone_number`        varchar(50)  NULL,
    `password`           varchar(500) NULL,
    `role_name`          varchar(500) NOT NULL DEFAULT 'ROLE_USER',
    `enabled`            boolean     NOT NULL default true,
    `created_date_time`  timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_external_id_unique` (`external_id`),
    UNIQUE KEY `user_email_unique` (`email`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `user_organizations`;

CREATE TABLE `user_organizations`
(
    `id`                 bigint    NOT NULL auto_increment,
    `user_id`             varchar(10)    NOT NULL,
    `org_id`                 bigint    NOT NULL,
    `external_id`        char(36)    NOT NULL,
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_org_association_unique` (`user_id`, `org_id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `user_password_reset_request`;

CREATE TABLE `user_password_reset_request`
(
    `id`                     bigint    NOT NULL auto_increment,
    `user_id`                varchar(10)   NOT NULL,
    `external_id`            char(36)    NOT NULL,
    `expire_date_time`       timestamp not null,
    `created_date_time`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_password_reset_request_unique` (`external_id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `driver_information`;

CREATE TABLE `driver_information`
(
    `id`                     bigint    NOT NULL auto_increment,
    `user_id`                varchar(10)    NOT NULL,
    `First_Name`         varchar(50) NOT NULL,
    `Middle_Name`         varchar(50) NULL,
    `Last_Name`          varchar(50) NOT NULL,
    `Drivers_Address`          varchar(150) NOT NULL,
    `Date_Of_Birth`         timestamp NOT NULL,
    `Gender`          	varchar(50) NOT NULL,
    `Relation`          	varchar(50) NOT NULL,
    `Drivers_License_Id_No`      	varchar(50) NOT NULL,
    `Drivers_License_Expiration`          	timestamp NOT NULL,
    `Upload_Photo`      varchar(250) NOT NULL,
    `created_date_time`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES user(`id`)
    
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;



