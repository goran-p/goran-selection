CREATE TABLE user(token BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
				  email VARCHAR(60) NOT NULL,
				  password VARCHAR(120) NOT NULL
);

CREATE TABLE city(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
				  name VARCHAR(128) NOT NULL,
				  description VARCHAR(256) NOT NULL,
				  population BIGINT NOT NULL,
				  recording_time TIMESTAMP NOT NULL
);

CREATE TABLE user_favorite_city(token BIGINT NOT NULL,
								city_id BIGINT NOT NULL,
								foreign key (token) references user(TOKEN),
								foreign key (city_id) references city(id)
);

CREATE UNIQUE INDEX usr0 ON user(email);
CREATE UNIQUE INDEX cty0 ON city(name);
CREATE UNIQUE INDEX ufc0 ON user_favorite_city(token, city_id);