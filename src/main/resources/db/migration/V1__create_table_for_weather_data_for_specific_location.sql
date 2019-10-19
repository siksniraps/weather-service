CREATE SCHEMA weather_service;

CREATE TABLE weather_service.weather (
    id identity primary key,
    ip varchar(50),
    city varchar(200),
    country varchar(100),
    latitude varchar(20),
    longitude varchar(20),
    description varchar(500),
    temperature double,
    temperature_min double,
    temperature_max double,
    pressure double,
    humidity double,
    wind_speed double,
    wind_direction_degree double
);

CREATE SEQUENCE weather_seq;