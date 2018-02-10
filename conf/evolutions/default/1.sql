# Users schema

# --- !Ups
DROP TABLE IF EXISTS Request;
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS ItemType;

CREATE TABLE User (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `pw` varchar(255) NOT NULL,
    `availability` varchar(255) NOT NULL
);

CREATE TABLE ItemType(
	`id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`rank` varchar(30),
	`description` varchar(255)
);

CREATE TABLE Item(
	`id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`itemTypeId` bigint(20) NOT NULL,
	`price` int(10),
	`count` int(10),
	`publishedDate` date,

	FOREIGN KEY (itemTypeId)
		REFERENCES ItemType(id)
);

CREATE TABLE Request (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `userId` bigint(20) NOT NULL,
    `requestType` ENUM('Sell', 'Buy') NOT NULL,
    `itemId` bigint(20) NOT NULL,
    FOREIGN KEY (userId)
		REFERENCES User(id),
    FOREIGN KEY (itemId)
		REFERENCES Item(id)		
);

# --- !Downs

DROP TABLE Request;
DROP TABLE Item;
DROP TABLE User;
DROP TABLE ItemType;