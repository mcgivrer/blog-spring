delete from yablog.blogusers;
insert into yablog.blogusers(username,password,email,firstname,lastname,last_connection,created_at,created_by,user_profile) values ('admin','nimda','admin@web-context.com','Admin','Administrator',TODAY(), TODAY(), 'admin','ADMIN');
delete from yablog.blogposts;
insert into yablog.blogposts(id,title,cover,header,content,created_at,created_by,locale,rated,status) values (1,'Welcome','http://localhost:8080/static/images/x360_blank_cover.jpg','Your first Welcome post','this is a first short post to welcome you onboard.',TODAY(),'admin','fr_FR','18+','PUBLISHED');