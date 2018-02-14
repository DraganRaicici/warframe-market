# Users schema

# --- !Ups
DROP TABLE IF EXISTS request;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS user;

CREATE TABLE user(
    `user_id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `pw` varchar(255) NOT NULL,
    `availability` varchar(255) NOT NULL
);

CREATE TABLE item(
	`item_id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`name` varchar(255),
	`item_type` enum('Mod','Weapon','Component','Relic') NOT NULL,
	`description` varchar(255),
	`link` varchar(255)
);

CREATE TABLE request(
    `request_id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL,
    `request_type` enum('Sell', 'Buy') NOT NULL,
    `item_id` bigint(20) NOT NULL,
    `rank` varchar(30),
    `price` int(10),
    `count` int(10),
    `publishedDate` date,

    FOREIGN KEY (user_id)
		REFERENCES user(user_id),
    FOREIGN KEY (item_id)
		REFERENCES item(item_id)
);

# --- !Downs

DROP TABLE request;
DROP TABLE item;
DROP TABLE user;
