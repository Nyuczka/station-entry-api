DROP TABLE station CASCADE;
DROP TABLE address CASCADE;
DROP TABLE ports_and_plugs CASCADE;
DROP TABLE charging_event CASCADE;

-- TO-DO: REFACTOR DATABASE STRUCTURE TO HAVE BETTER PERFORMANCE --

CREATE TABLE station
(
    name        varchar(100) primary key,
    mac_address varchar(20),
    evse_id     varchar(50) default 'N/A',
    org_name    varchar(100) default 'City of Palo Alto'
);

CREATE TABLE address
(
    street       varchar(100),
    city         varchar(50) default 'Palo Alto',
    county       varchar(50) default 'N/A',
    state        varchar(50) default 'California',
    postal_code  varchar(5)  default '94301',
    country      varchar(50) default 'United States',
    station_name varchar(100) references station (name)
);

CREATE TABLE ports_and_plugs
(
    port_number smallint primary key,
    port_type   varchar(50) default 'N/A',
    plug_type   varchar(50) default 'N/A'
);

CREATE TABLE charging_event
(
    plug_in_event_id bigint,
    start_date       timestamp,
    end_date         timestamp,
    total_duration   varchar(50),
    charging_time    varchar(50),
    energy           float,
    ghg_savings      float,
    gasoline_savings float,
    ended_by         varchar(50) default 'N/A',
    user_id          varchar(50) default 'N/A',
    transaction_date timestamp,
    fee              decimal     default 0,
    currency         VARCHAR(4)  default 'USD',
    station_name     varchar(100) references station (name),
    port             integer references ports_and_plugs (port_number)
);

CREATE
INDEX event_idx on charging_event(station_name, user_id);