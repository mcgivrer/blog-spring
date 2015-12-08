-- CREATE SCHEMA
create schema public.yablog;
using public.yablog;
-- DROP EXISTING TABLES
drop table blogusers if exists;
drop table blogposts if exists;
-- CREATE USERS
create table blogusers {
 username VARCHAR(40) PRIMARY KEY,
 password VARCHAR(50) NOT NULL,
 email VARCHAR(255) NOT NULL,
 firstname VARCHAR(60),
 lastname VARCHAR(60),
 last_connection DATE,
 created_at DATE,
 created_by VARCHAR(40)
 };
-- CREATE POSTS 
create table blogposts {
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
 };
 