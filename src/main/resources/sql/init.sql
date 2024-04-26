create table ph_function(
        id   bigint(11)
            primary key,
        mode int not null default 3,
        desc varchar(256) not null default '',
        is_delete        tinyint(1)           not null default 0,
        status        tinyint(1)           not null default 0
);