use movieapp;

-- Adding all original data attributes
drop table person;
create table person(
                       ActorID varchar(10),
                       CastName varchar(255),
                       BirthName varchar(255),
                       Height double,
                       Bio varchar(13000),
                       BirthDetails varchar(255),
                       BirthYear integer,
                       Birthday  varchar(10),
                       PlaceOfBirth varchar(255),
                       deathDetails varchar(1000),
                       deathYear varchar(8),
                       DateOfDeath  varchar(10),
                       PlaceOfDeath varchar(255),
                       ReasonOfDeath varchar(255),
                       Spouses integer,
                       divorces integer,
                       SpousesWithChild integer,
                       children integer,
                       MainOccupation varchar(255),
                       KnownForTitles varchar(255),
                       primary key(ActorID)
);

ALTER TABLE person ALTER deathYear SET DEFAULT 0;

-- LOAD PERSON
-- IMDb namesOrig.csv

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/IMDb namesOrig.csv' INTO TABLE person
    FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n';