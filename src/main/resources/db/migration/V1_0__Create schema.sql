-- CREATE SCHEMA
drop schema yablog if exists;
create schema yablog;
-- DROP EXISTING TABLES
drop table yablog.blogusers if exists;
drop table yablog.blogposts if exists;
-- CREATE USERS
create table yablog.blogusers (
 username VARCHAR(40) PRIMARY KEY,
 password VARCHAR(50) NOT NULL,
 email VARCHAR(255) NOT NULL,
 firstname VARCHAR(60),
 lastname VARCHAR(60),
 last_connection DATE,
 created_at DATE,
 created_by VARCHAR(40),
 user_profile VARCHAR(10)
 );
-- CREATE POSTS 
create table yablog.blogposts (
 id INTEGER PRIMARY KEY,
 title VARCHAR(100) NOT NULL,
 cover VARCHAR(255),
 header VARCHAR(300),
 content VARCHAR(4000) NOT NULL,
 rated VARCHAR(10),
 locale VARCHAR(10),
 STATUS CHAR(10) NOT NULL,
 created_at DATE,
 created_by VARCHAR(40)
 );
 
