USE placeholder;

CREATE TABLE IF NOT EXISTS users
(
	id INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(256) NOT NULL,
    PRIMARY KEY(id)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS task_lists
(
	id INT AUTO_INCREMENT NOT NULL,
    user_id INT NOT NULL,
    name varchar(20),
   primary key (id),
	FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS tasks
(
	id INT AUTO_INCREMENT,
    list_id INT NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_done BOOLEAN default 0,
    PRIMARY KEY (id),
    FOREIGN KEY (list_id) references task_lists(id) ON DELETE CASCADE
) ENGINE=INNODB ;