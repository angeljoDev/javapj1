CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20) UNIQUE,
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId INT PRIMARY KEY auto_increment,
    filename VARCHAR,
    contenttype VARCHAR,
    filesize VARCHAR,
    userid INT,
    filedata BLOB,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialid INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    userid INT,
    foreign key (userid) references USERS(userid)
);
*******************************************************************************
*****************************************NOTES:********************************
*******************************************************************************
*/If you are using Postgres please run the following queries:/*


CREATE TABLE IF NOT EXISTS USERS (
  userid SERIAL PRIMARY KEY,
  username VARCHAR(20) UNIQUE,
  salt VARCHAR(100),
  password VARCHAR(100),
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteid SERIAL PRIMARY KEY,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    userid INT,
    FOREIGN KEY (userid) REFERENCES USERS(userid)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId SERIAL PRIMARY KEY,
    filename VARCHAR,
    contenttype VARCHAR,
    filesize VARCHAR,
    userid INT,
    filedata BYTEA,
    FOREIGN KEY (userid) REFERENCES USERS(userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialid SERIAL PRIMARY KEY,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    userid INT,
    FOREIGN KEY (userid) REFERENCES USERS(userid)
);
