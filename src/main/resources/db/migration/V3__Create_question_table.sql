CREATE TABLE IF NOT EXISTS table_questions (
	id INT AUTO_INCREMENT,
	title VARCHAR(100),
	description TEXT,
	creator INT,
	comment_count INT DEFAULT 0,
	browse_count INT DEFAULT 0,
	like_count INT DEFAULT 0,
	tag VARCHAR(256),
	gmt_create BIGINT,
	gmt_modified BIGINT,
	PRIMARY KEY(id)
)