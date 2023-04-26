delimiter $$
# LOGIN FUNCTION
drop function if exists login $$
create function login(usernameIn varchar(30), passwordIn varchar(30))
returns boolean
deterministic
begin
	return IF(
    (select password from users where username = usernameIn) = passwordIn, True, false);
end $$

# CAUGHT PROCEDURE
drop procedure if exists caught $$
create procedure caught(user_name_in varchar(30), pokemon_name_in varchar(30), level_in int) # java custom exception for level over 100
begin
	IF NOT( SELECT EXISTS 
		(SELECT * FROM collected WHERE user_name = user_name_in and pokemon_name = pokemon_name_in) ) 
	THEN 
		insert into collected(user_name, pokemon_name, level, completed) 
			values (user_name_in, pokemon_name_in, level_in, (select if (level_in = 100, True, False) ) ) ;
	END IF;
    
end $$

# LEVEL UP PROCEDURE
drop procedure if exists levelUp $$ # if trigger on level is not made, update collected here
create procedure levelUp(user_name_in varchar(30), pokemon_name_in varchar(30), new_level int) 
begin
	IF ( SELECT EXISTS 
		(SELECT * FROM collected WHERE user_name = user_name_in and pokemon_name = pokemon_name_in) ) 
	then 
		update collected set level = new_level  WHERE user_name = user_name_in and pokemon_name = pokemon_name_in;
	end if;
end $$

# PROGRESS PROCEDURE
drop PROCEDURE if exists get_collection $$
CREATE PROCEDURE get_collection(user_name_in varchar(30))
BEGIN
    select * from collected where user_name = user_name_in;
end$$

# PROGRESS gotta_catchem
drop PROCEDURE if exists gotta_catchem $$
CREATE PROCEDURE gotta_catchem(user_name_in varchar(30))
BEGIN
    select * from pokemon where name not in(select pokemon_name from collected where user_name = user_name_in) order by id;
end$$

# SEARCH PROCEDURE
drop PROCEDURE if exists get_pokemon $$
CREATE PROCEDURE get_pokemon(user_name_in varchar(30), pokemon_name_in varchar(30))
BEGIN
    select * from collected where user_name = user_name_in and pokemon_name = pokemon_name_in;
end$$

# get all pokemon
drop PROCEDURE if exists get_all_pokemon $$
CREATE PROCEDURE get_all_pokemon()
BEGIN
    select * from pokemon order by id;
end$$

# ADD NEW USER
drop procedure if exists add_new_user $$
create procedure add_new_user(user_name_in varchar(30), password_in varchar(30))
Begin
	insert into users(username, password) 
			values (user_name_in, password_in);
end$$

##############
-- TRIGGERS --
##############
drop trigger if exists collectedCheck $$
create trigger collectedCheck 
before update 
on 
collected 
for each row 
BEGIN 
	if new.level >= 100 then set new.completed = true;
    elseif new.level < 100 then set new.completed = false;
    end if;
END$$


delimiter ;
  --   
call caught('username', 'pikachu', 5);
call caught('username', 'charmander', 5);
-- call caught('username2', 'Bulbasaur', 5);
-- call levelUp('username','pikachu', 100);
-- call levelUp('username','pikachu', 10);

-- call get_pokemon('username', 'pikachu');
-- call get_pokemon('username2', 'oddish');


-- call get_collection('username');
-- truncate collected;
-- -- truncate users;
-- -- truncate pokemon;

-- select * from users;
-- call add_new_user('username5', 'password');
-- call get_all_pokemon();

call get_collection('username');
call gotta_catchem('username');






