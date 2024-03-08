USE CustomerDB;

CREATE TABLE IF NOT EXISTS customer_table
(
    id          INT         NOT NULL,
    firstName   VARCHAR(16) NOT NULL,
    lastName    VARCHAR(16) NOT NULL,
    phoneNumber INT(8)      NULL,
    location    VARCHAR(32) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX phoneNumber_UNIQUE (phoneNumber ASC)
);


INSERT INTO customer_table (id, firstName, lastName, phoneNumber, location)
VALUES (1, 'John', 'Doe', 11234567, 'Jerusalem'),
       (2, 'Jane', 'Doe', 12345678, 'Ramallah'),
       (3, 'John', 'Smith', 23456789, 'Bethlehem'),
       (4, 'Jane', 'Smith', 34567890, 'Al-Khalil'),
       (5, 'Alice', 'Johnson', 45678901, 'Nablus'),
       (6, 'Bob', 'Williams', 56789012, 'Gaza'),
       (7, 'Emma', 'Brown', 78965432, 'Jenin'),
       (8, 'David', 'Martinez', 78901234, 'Tulkarm'),
       (9, 'Olivia', 'Garcia', 14725896, 'Qalqilya'),
       (10, 'James', 'Wilson', 90123456, 'Ariha'),
       (11, 'Sophia', 'Lee', 45678902, 'Beit Sahour'),
       (12, 'Michael', 'Lopez', 78901235, 'Beit Jala'),
       (13, 'Isabella', 'Taylor', 56789011, 'Khan Yunis'),
       (14, 'William', 'Harris', 89012345, 'Rafah'),
       (15, 'Charlotte', 'Clark', 67890123, 'Beit Hanoun'),
       (16, 'Daniel', 'Young', 25896314, 'Jerusalem'),
       (17, 'Amelia', 'Lewis', 91472589, 'Ramallah'),
       (18, 'Benjamin', 'Allen', 23456777, 'Bethlehem'),
       (19, 'Mia', 'Walker', 78901222, 'Nablus'),
       (20, 'Ethan', 'King', 34567888, 'Gaza'),
       (21, 'Abigail', 'Green', 56789013, 'Jenin'),
       (22, 'Alexander', 'Baker', 45698731, 'Tulkarm'),
       (23, 'Harper', 'Adams', 34567891, 'Qalqilya'),
       (24, 'Ryan', 'Hill', 14796302, 'Ariha'),
       (25, 'Evelyn', 'Campbell', 56789014, 'Beit Sahour'),
       (26, 'Noah', 'Mitchell', 12365497, 'Beit Jala'),
       (27, 'Avery', 'Roberts', 67890321, 'Khan Yunis'),
       (28, 'Grace', 'Carter', 74185263, 'Rafah'),
       (29, 'Liam', 'Phillips', 12345666, 'Beit Hanoun'),
       (30, 'Chloe', 'Evans', 56789111, 'Jerusalem');
