create table comment
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    text        varchar(255) not null,
    schedule_id bigint       null,
    user_id     bigint       null,
    constraint FK8kcum44fvpupyw6f5baccx25c
        foreign key (user_id) references user (id),
    constraint FKsy51iks4dgapu66gfj3mnykch
        foreign key (schedule_id) references schedule (id)
);

create table schedule
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    title       varchar(255) not null,
    todo        varchar(255) not null,
    user_id     bigint       null,
    constraint FKa50n59y1j4a6qwa42p8jiguds
        foreign key (user_id) references user (id)
);

create table user
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    email       varchar(255) not null,
    password    varchar(255) not null,
    username    varchar(255) not null,
    constraint UKob8kqyqqgmefl0aco34akdtpe
        unique (email)
);

create table user_schedule
(
    schedule_id bigint not null,
    user_id     bigint not null,
    constraint FKdd4cwg959bmy4551iiivx4wdw
        foreign key (schedule_id) references schedule (id),
    constraint FKmsyiiyw4bv8y8sv4dbh6k481a
        foreign key (user_id) references user (id)
);

