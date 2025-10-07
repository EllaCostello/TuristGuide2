CREATE SCHEMA IF NOT EXISTS attractions;
SET SCHEMA attractions;


DROP TABLE IF EXISTS attraction_tag;
DROP TABLE IF EXISTS attraction;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS city;

CREATE TABLE city (
                      cityID INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(255)
);

CREATE TABLE tag (
                     tagID INT PRIMARY KEY AUTO_INCREMENT,
                     name VARCHAR(255)
);

CREATE TABLE attraction (
                            attractionID INT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            description VARCHAR(255) NOT NULL,
                            cityID INT NOT NULL,
                            FOREIGN KEY (cityID) REFERENCES city(cityID)
);

CREATE TABLE attraction_tag (
                                attractionID INT NOT NULL,
                                tagID INT NOT NULL,
                                PRIMARY KEY (attractionID, tagID),
                                FOREIGN KEY (attractionID) REFERENCES attraction(attractionID) ON DELETE CASCADE,
                                FOREIGN KEY (tagID) REFERENCES tag(tagID) ON DELETE CASCADE
);

INSERT INTO city (name) VALUES
                            ('København'),
                            ('Odense'),
                            ('Aalborg'),
                            ('Aarhus');

INSERT INTO tag (name) VALUES
                           ('Børnevenlig'),
                           ('Gratis'),
                           ('Dyrt'),
                           ('Kunst'),
                           ('Koncert'),
                           ('Museum'),
                           ('Natur'),
                           ('Forlystelsespark');

INSERT INTO attraction (name, description, cityID) VALUES
                                                       ('Tivoli', 'Verdens ældste tivoli', 1),
                                                       ('Den Lille Havfrue', 'Verdens ældste havfrue', 1),
                                                       ('Operaen', 'Verdens ældste opera', 1);

INSERT INTO attraction_tag (attractionID, tagID) VALUES
                                                     (1, 1),
                                                     (1, 3),
                                                     (1, 8),
                                                     (2, 1),
                                                     (2, 2),
                                                     (2, 4),
                                                     (3, 3),
                                                     (3, 4),
                                                     (3, 5);
