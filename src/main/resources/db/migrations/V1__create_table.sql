create table weather(
	id integer auto_increment not null,
    location varchar(128) not null,
    date date not null,
    time time not null,
    temperature integer not null,
    wind_speed integer not null,
    pressure integer not null,
    humidity integer not null,
    weather_conditions varchar(128) not null,
    primary key (id)
)