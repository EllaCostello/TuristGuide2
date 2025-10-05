
INSERT INTO attractions.city (name)
VALUES
    ('København'),
    ('Odense'),
    ('Aalborg'),
    ('Aarhus');

INSERT INTO attractions.tag (name)
    VALUE
    ("Børnevenlig"),
    ("Gratis"),
    ("Dyrt"),
    ("Kunst"),
    ("Koncert"),
    ("Museum"),
    ("Natur"),
    ("Forlystelsespark");

INSERT INTO attractions.attraction (name, description, cityID)
    VALUE
    ("Tivoli", "Verdens ældste tivoli", 1),
    ("Den Lille Havfrue", "Verdens ældste havfrue", 1),
    ("Operaen", "Verdens ældste opera", 1);

INSERT INTO attractions.attraction_tag (attractionID, tagID)
    VALUE
    (1, 1),
    (1, 3),
    (1, 8),
    (2, 1),
    (2, 2),
    (2, 4),
    (3, 3),
    (3, 4),
    (3, 5);
