CREATE TABLE IF NOT EXISTS table_comment (
	id BIGINT AUTO_INCREMENT NOT NULL,
	parent_id BIGINT NOT NULL,
	type_id BIGINT,
	commentator BIGINT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	like_count BIGINT DEFAULT 0,
	PRIMARY KEY(id)
)