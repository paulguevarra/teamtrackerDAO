SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams (
    id int PRIMARY KEY auto_increment,
    teamname VARCHAR,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS members (
    memberId int PRIMARY KEY auto_increment,
    name VARCHAR,
    teamId INTEGER
);