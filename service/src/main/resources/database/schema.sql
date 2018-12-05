create table category (id bigint not null, description varchar(255), name varchar(255), creator_id bigint, primary key (id));
create table expense (id bigint not null, description varchar(255), category_id bigint, creator_id bigint, primary key (id));
create table user (id bigint not null, username varchar(255), primary key (id));
create table audit (id bigint not null, auditedEntityClassName varchar(255), auditedEntityId bigint,primary key (id));

alter table category add constraint fk_category_user foreign key (creator_id) references user;
alter table expense add constraint fk_expense_category foreign key (category_id) references category;
alter table expense add constraint fk_expense_user foreign key (creator_id) references user;