Hibernate
:

create table Comment
(
    id         bigint       not null,
    text       varchar(255),
    user_id    varchar(255) not null,
    message_id bigint,
    primary key (id)
) engine=InnoDB
Hibernate:

create table hibernate_sequence
(
    next_val bigint
) engine=InnoDB
Hibernate:

    insert into hibernate_sequence values ( 1 )
Hibernate:

create table Message
(
    id              bigint not null,
    creationDate    datetime(6),
    link            varchar(255),
    linkCover       varchar(255),
    linkDescription varchar(255),
    linkTitle       varchar(255),
    text            varchar(255),
    user_id         varchar(255),
    primary key (id)
) engine=InnoDB
Hibernate:

create table user_role
(
    user_id varchar(255) not null,
    roles   varchar(255)
) engine=InnoDB
Hibernate:

create table user_subscription
(
    active        bit          not null,
    subscriber_id varchar(255) not null,
    channel_id    varchar(255) not null,
    primary key (channel_id, subscriber_id)
) engine=InnoDB
Hibernate:

create table user_table
(
    id             varchar(255) not null,
    activationCode varchar(255),
    active         bit          not null,
    email          varchar(255),
    lastVisit      datetime(6),
    password       varchar(255),
    password2      varchar(255),
    username       varchar(255),
    primary key (id)
) engine=InnoDB
Hibernate:

alter table Comment
    add constraint FK4lqnj8d9jhaf1g36sqcvoolky
        foreign key (user_id)
            references user_table (id) Hibernate:

alter table Comment
    add constraint FKr6obs0bvtyg53rqlwu3hmg7mw
        foreign key (message_id)
            references Message (id) Hibernate:

alter table Message
    add constraint FK1ysnytiish3f3owfydygbpjrf
        foreign key (user_id)
            references user_table (id) Hibernate:

alter table user_role
    add constraint FK5cwpllhe458f6j6fb7rmhfyt2
        foreign key (user_id)
            references user_table (id) Hibernate:

alter table user_subscription
    add constraint FKl6fh3s6d70y4386cf4cgb48v7
        foreign key (channel_id)
            references user_table (id) Hibernate:

alter table user_subscription
    add constraint FKfltgt8f1m2q76hw7rg2i5yc7v
        foreign key (subscriber_id)
            references user_table (id)