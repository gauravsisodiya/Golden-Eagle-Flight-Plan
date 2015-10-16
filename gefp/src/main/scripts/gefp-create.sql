
    create table authorities (
        user_id int4 not null,
        role varchar(255)
    );

    create table cell (
        id int4 not null,
        plan_id int4,
        runway_id int4,
        stage_id int4,
        primary key (id)
    );

    create table checkpoint (
        id int4 not null,
        orderId int4,
        description varchar(255),
        cell_id int4,
        primary key (id)
    );

    create table department (
        id int4 not null,
        name varchar(255),
        currentPlan_id int4,
        primary key (id)
    );

    create table plan (
        id int4 not null,
        name varchar(255),
        publishedDate timestamp,
        department_id int4,
        primary key (id)
    );

    create table plan_checkpoint (
        plan_id int4 not null,
        checkpoints_id int4 not null
    );

    create table plan_runway (
        plan_id int4 not null,
        runways_id int4 not null
    );

    create table plan_stage (
        plan_id int4 not null,
        stages_id int4 not null
    );

    create table runway (
        id int4 not null,
        name varchar(255),
        primary key (id)
    );

    create table stage (
        id int4 not null,
        name varchar(255),
        primary key (id)
    );

    create table users (
        id int4 not null,
        cin varchar(255) not null,
        emailId varchar(255),
        enabled boolean not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255),
        username varchar(255),
        major_id int4,
        plan_id int4,
        primary key (id)
    );

    create table users_checkpoint (
        users_id int4 not null,
        checkpoints_id int4 not null,
        primary key (users_id, checkpoints_id)
    );

    alter table users 
        add constraint UK_ka6m8ghsr7vna1ti6lftwww8o unique (cin);

    alter table authorities 
        add constraint FK_s21btj9mlob1djhm3amivbe5e 
        foreign key (user_id) 
        references users;

    alter table cell 
        add constraint FK_5709j5ibsgsvgsgmsywuolwsp 
        foreign key (plan_id) 
        references plan;

    alter table cell 
        add constraint FK_9y92mmkrji884v27sj99txci2 
        foreign key (runway_id) 
        references runway;

    alter table cell 
        add constraint FK_k59ltkw6rs6v4xgm4a0aqwc6d 
        foreign key (stage_id) 
        references stage;

    alter table checkpoint 
        add constraint FK_naq2x308i00jwtw76n97lxghk 
        foreign key (cell_id) 
        references cell;

    alter table department 
        add constraint FK_nhxn01f9ds28j9r2ggytm2awo 
        foreign key (currentPlan_id) 
        references plan;

    alter table plan 
        add constraint FK_1ckna5ptp81j3dc03l57yfw35 
        foreign key (department_id) 
        references department;

    alter table plan_checkpoint 
        add constraint FK_rr10t78aj1lheiwbkjwjqi368 
        foreign key (checkpoints_id) 
        references checkpoint;

    alter table plan_checkpoint 
        add constraint FK_nwuo4a6j2b2i32qk01pn6bgb2 
        foreign key (plan_id) 
        references plan;

    alter table plan_runway 
        add constraint FK_hhmhwkm7nevlv6o7vs3pxhrv4 
        foreign key (runways_id) 
        references runway;

    alter table plan_runway 
        add constraint FK_n2i5dj18t1dcbbhvs5ka7uv8t 
        foreign key (plan_id) 
        references plan;

    alter table plan_stage 
        add constraint FK_p46aecqfvadlul0kbs5jjr706 
        foreign key (stages_id) 
        references stage;

    alter table plan_stage 
        add constraint FK_ky7nuowbhfrg96wiud1p1lt07 
        foreign key (plan_id) 
        references plan;

    alter table users 
        add constraint FK_q37jte7r1ptl16arimkk23y1h 
        foreign key (major_id) 
        references department;

    alter table users 
        add constraint FK_km7rd8sgwa1qls24gkxoh2b2i 
        foreign key (plan_id) 
        references plan;

    alter table users_checkpoint 
        add constraint FK_kk1wdt5gu7m2uqpgdkebgjnlm 
        foreign key (checkpoints_id) 
        references checkpoint;

    alter table users_checkpoint 
        add constraint FK_2nw3e5rex5t96a130r3h01lc5 
        foreign key (users_id) 
        references users;

    create sequence hibernate_sequence minvalue 100;



insert into stage values(1,'Pre-College (pre-flight checklist)');
insert into stage values(2,'Freshman');


insert into runway values(1,'Academics');
insert into runway values(2,'Career Preparation');
insert into runway values(3,'Leadership & Community Engagement');
insert into runway values(4,'Sports & Fitness Program');

insert into department values(1,'Computer Science',null);
insert into department values(2,'Electrical Engg',null);

insert into plan values(1,'Computer Science v1','2015-03-05 16:38:00.222',1);
insert into plan values(2,'Electrical Engg v1','2015-03-05 16:38:00.222',2);

update department set currentplan_id = 1 where id = 1;
update department set currentplan_id = 2 where id = 2;

insert into cell values(1,1,1,1);
insert into cell values(2,1,2,1);
insert into cell values(3,1,3,1);
insert into cell values(4,2,1,1);
insert into cell values(5,2,4,1);
insert into cell values(6,1,1,2);
insert into cell values(7,1,2,2);
insert into cell values(8,1,3,2);

insert into checkpoint values(1,0,'Algebra (before Yr1)',1);
insert into checkpoint values(2,0,'Apply for financial aid and scholarships',2);
insert into checkpoint values(3,0,'Make a list of questions to ask during orientation and ask them!',3);
insert into checkpoint values(4,1,'Join Gym and Choose One Sport',1);
insert into checkpoint values(5,0,'EE Welcome Kit',4);
insert into checkpoint values(6,1,'EE Seminar',4);
insert into checkpoint values(7,0,'EE 422',5);
insert into checkpoint values(8,0,'CS312',6);
insert into checkpoint values(9,0,'CS320',7);
insert into checkpoint values(10,0,'CS520',8);

insert into users values (1,'304393900','jdoe1@calstatela.edu','t','John','Doe','abcd','jdoe1',1,1);
insert into users values (2,'304393901','jdoe2@calstatela.edu','t','John','Doe','abcd','jdoe2',2,2);
insert into users values (3,'304393902','csun@calstatela.edu','t','Chengyu','Sun','abcd','cysun');
insert into users values (4,'304393903','tfox@calstatela.edu','t','Tom','Fox','abcd','tfox');

insert into authorities values(1,'ROLE_STUDENT');
insert into authorities values(2,'ROLE_STUDENT');
insert into authorities values(3,'ROLE_ADMIN');
insert into authorities values(4,'ROLE_ADVISOR');

insert into users_checkpoint values(1,1);
insert into users_checkpoint values(2,4);
insert into users_checkpoint values(2,1);	

insert into plan_runway values(1,1);
insert into plan_runway values(1,2);
insert into plan_runway values(1,3);
insert into plan_runway values(2,1);
insert into plan_runway values(2,4);

insert into plan_stage values(1,1);
insert into plan_stage values(2,1);
insert into plan_stage values(1,2);

insert into plan_checkpoint values(1,1);
insert into plan_checkpoint values(1,2);
insert into plan_checkpoint values(1,3);
insert into plan_checkpoint values(2,1);
insert into plan_checkpoint values(2,4);









