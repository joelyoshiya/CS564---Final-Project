-- Find similar movies by actor
USE movieapp;
-- Start
delimiter $$
drop procedure if exists getMoviesWithLikedActors;
create procedure getMoviesWithLikedActors(IN UserPassword VARCHAR(255), UserName VARCHAR(255))
begin
    select Title
        from movie m, likedmovie l
            where ID = (select ID
            -- IN PROGRESS
                from WorkedOn
                where WorkedOn.ID = )
end $$


-- find People by program User's Like people
delimiter $$
drop procedure if exists getLikedPeopleFromProgUser;
create procedure getLikedPeopleFromProgUser(IN UserPassword VARCHAR(255), UserName VARCHAR(255))
begin
    Select p.CastName
    FROM person p, LikedPeople l
    WHERE l.UserName = UserPassword AND I.UserPassword = UserName;
end $$

-- Find Movies by Program Users Liked content
Select m.title from movie m, likedmovie l where l.UserName=’testuser’ and l.UserPassword = ‘12345’;
delimiter $$
drop procedure if exists getLikedMoviesProgUser;
create procedure getLikedMoviesProgUser(IN UserPassword VARCHAR(255), UserName VARCHAR(255))
begin
    Select m.Title
    FROM movie m , LikedPeople l
    WHERE l.UserName = UserPassword AND l.UserPassword = UserName;
end $$