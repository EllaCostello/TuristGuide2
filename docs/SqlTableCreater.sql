DROP DATABASE IF EXISTS attractions;
CREATE DATABASE attractions
	DEFAULT CHARACTER SET utf8mb4;
USE attractions;


CREATE TABLE city (
                      cityID INT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255),
                      PRIMARY KEY (cityID)
);

CREATE TABLE tag (
                     tagID INT NOT NULL AUTO_INCREMENT,
                     name varchar(255),
                     PRIMARY KEY (tagID)
);
CREATE TABLE attraction (
                            attractionID INT NOT NULL AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            description VARCHAR(255) NOT NULL,
                            cityID INT NOT NULL,
                            FOREIGN KEY (cityID) REFERENCES city (cityID),
                            PRIMARY KEY (attractionID)

);

CREATE TABLE attraction_tag (
                                attractionID INT NOT NULL,
                                tagID INT NOT NULL,
                                PRIMARY KEY (attractionID, tagID),
                                FOREIGN KEY (attractionID) REFERENCES attraction (attractionID) ON DELETE CASCADE,
                                FOREIGN KEY (tagID) REFERENCES tag (tagID) ON DELETE CASCADE
);
