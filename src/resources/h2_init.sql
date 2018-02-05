CREATE TABLE Movies (
    id INT NOT NULL,
    title VARCHAR(255)  NOT NULL,
    year INT  NOT NULL,
    image VARCHAR(255),

    PRIMARY KEY (ID)
);

CREATE SEQUENCE movies_id START WITH 0;