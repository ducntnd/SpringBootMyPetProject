insert into User(username, password, enabled)
    values ('user','pass',true);

insert into User(username, password, enabled)
    values ('admin','pass',true);

insert into User(username, authority)
    values ('user','ROLE_USER');

insert into User(username, authority)
    values ('admin','ROLE_ADMIN');