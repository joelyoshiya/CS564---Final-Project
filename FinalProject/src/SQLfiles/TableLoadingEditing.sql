use movieapp;


-- LOAD MOVIE
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/IMDb moviesRef.csv' INTO TABLE movie
    FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';
-- LOAD PERSON
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/IMDb namesRef.csv' INTO TABLE person
    FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';
-- LOAD PLACE
ALTER TABLE place ALTER Population SET DEFAULT 0;
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/worldcitiespopRef.csv' INTO TABLE place
    FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';


-- Reformat Place to match the csv
DROP TABLE Place;
-- ALTER TABLE Place ADD COLUMN Country VARCHAR(2), ADD COLUMN City VARCHAR(255), ADD COLUMN AccentCity VARCHAR(255), ADD COLUMN Region VARCHAR(2), ADD COLUMN Latitude double, ADD COLUMN Longitude double, ADD PRIMARY KEY(City, Country, Region);
create table Place(Country VARCHAR(2),
                   City VARCHAR(255),
                   AccentCity VARCHAR(255),
                   Region VARCHAR(2),
                   Population BIGINT,
                   Latitude double,
                   Longitude double,
                   PRIMARY KEY(City, Country, Region)
);

-- Reformat movie DESCRIPTION added
-- DROP TABLE likedmovie;
DROP TABLE movie;
create table movie(
                      ID varchar(9),
                      Title varchar(255),
                      Description varchar(1000),
                      dateOfRelease varchar(10),
                      MovieGenre varchar(511),
                      Duration int,
                      country varchar(255),
                      Writers varchar(511),
                      worldgross BIGINT,
                      Director varchar(255),
                      mLanguage varchar(255),
                      listofactors varchar(8000),
                      primary key(ID));
-- Reformat Person, ID must be 10 not 9
drop table likedpeople;
drop table person;
create table person(
    ActorID varchar(10),
        CastName varchar(255),
         Height double,
          Birthday  varchar(10),
           PlaceOfBirth varchar(255),
            DateOfDeath  varchar(10),
             MainOccupation varchar(255),
              KnownForTitles varchar(255),
               primary key(ActorID)
                   );
