#---------------------  DROP-ON-STARTUP  ---------------------#
DROP TABLE IF EXISTS station CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS ports_and_plugs CASCADE;
DROP TABLE IF EXISTS charging_event CASCADE;


#---------------------  TABLES  ---------------------#
CREATE TABLE IF NOT EXISTS station
(
    name        varchar(100) primary key,
    mac_address varchar(20),
    evse_id     varchar(50) default 'N/A',
# TO-DO: add system_sn and model_nr fields
#     system_sn   varchar(50),
#     model_nr    varchar(50),
    org_name    varchar(100) default 'City of Palo Alto'
);

CREATE TABLE IF NOT EXISTS address
(
    id           smallint primary key auto_increment,
    street       varchar(100),
    city         varchar(50) default 'Palo Alto',
    county       varchar(50) default 'N/A',
    state        varchar(50) default 'California',
    postal_code  varchar(5)  default '94301',
    country      varchar(50) default 'United States',
    station_name varchar(100) references station(name)
);

# TO-DO: add the location table
# CREATE TABLE location
# (
#   latitude decimal,
#   longitude decimal,
#   station_name varchar(50) references station(name),
#   port varchar(50) references ports_and_plugs(port_number)
# );

CREATE TABLE IF NOT EXISTS ports_and_plugs
(
    port_number smallint primary key,
    port_type   varchar(50) default 'N/A',
    plug_type   varchar(50) default 'N/A'
);

CREATE TABLE IF NOT EXISTS charging_event
(
    plug_in_event_id bigint,
    start_date       datetime,
    end_date         datetime,
    total_duration   time default 0,
    charging_time    time default 0,
    energy           decimal,
    ghg_savings      decimal,
    gasoline_savings decimal,
    ended_by         varchar(50) default 'N/A',
    user_id          varchar(50) default 'N/A',
    transaction_date datetime,
    fee              decimal,
    currency         VARCHAR(4)  default 'USD',
    station_name     varchar(100) references station (name),
    port             integer references ports_and_plugs (port_number)
);


#---------------------  INDEXES  ---------------------#
# TO-DO: add index for location
# CREATE INDEX location_idx on location(port, station_name);
CREATE INDEX station_idx on address(station_name);
CREATE INDEX event_idx on charging_event(station_name, user_id, port);