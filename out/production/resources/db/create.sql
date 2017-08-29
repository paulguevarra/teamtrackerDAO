SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams (
    teamid int PRIMARY KEY auto_increment,
    teamname VARCHAR,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS members (
    memberid int PRIMARY KEY auto_increment,
    membername VARCHAR,
    teamid INTEGER
);