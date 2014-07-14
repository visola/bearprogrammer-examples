create table User (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(1024) NOT NULL
);

create table Authority (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	authority VARCHAR(255) NOT NULL
);

insert into Authority (id,authority) values
	(1,'admin'),
	(2,'user');
	
create table User_Authority (
	user_id INTEGER NOT NULL,
	authorities_id INTEGER NOT NULL,
	FOREIGN KEY (user_id) REFERENCES User(id),
	FOREIGN KEY (authorities_id) REFERENCES Authority(id)
);