alter table table_questions modify id BIGINT auto_increment;
alter table table_questions modify creator BIGINT not null;
create unique index table_questions_id_uindex on table_questions (id);

alter table table_user modify id BIGINT auto_increment;
create unique index table_user_id_uindex on table_user (id);
