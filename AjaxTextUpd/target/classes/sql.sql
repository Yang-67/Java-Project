create database discuss;

-- auto-generated definition
create table user
(
    user_id  int(255) auto_increment
        primary key,
    username char(30) not null,
    password char(30) not null,
    identity int(2)   not null
);


-- auto-generated definition
create table content
(
    user_id   int(255)           not null,
    title_id  int(255) auto_increment
        primary key,
    title     char(255)          not null,
    content   char(255)          not null,
    attribute int(255) default 0 not null,
    top       int(255) default 0 not null,
    important int(255) default 0 not null,
    time      timestamp          null,
    constraint content_ibfk_1
        foreign key (user_id) references user (user_id)
);

create index user_id
    on content (user_id);


-- auto-generated definition
create table reply
(
    user_id  int(255)           not null,
    title_id int(255)           not null,
    reply_id int(255) auto_increment
        primary key,
    rcontent char(255)          not null,
    dz       int(255) default 0 not null,
    rtime    datetime           null,
    constraint reply_ibfk_1
        foreign key (user_id) references user (user_id),
    constraint reply_ibfk_2
        foreign key (title_id) references content (title_id)
);

create index title_id
    on reply (title_id);

create index user_id
    on reply (user_id);



-- auto-generated definition
create table is_attribute
(
    user_id      int(255) not null,
    title_id     int(255) not null,
    attribute_id int(255) auto_increment
        primary key,
    constraint is_attribute_ibfk_1
        foreign key (user_id) references user (user_id),
    constraint is_attribute_ibfk_2
        foreign key (title_id) references content (title_id)
);

create index title_id
    on is_attribute (title_id);

create index user_id
    on is_attribute (user_id);

