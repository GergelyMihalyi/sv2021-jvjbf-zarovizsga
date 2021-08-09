create table teams(id bigint auto_increment, name varchar(255) not null,  primary key(id));
create table players(id bigint auto_increment,birth_date date, name varchar(255) not null,
 position ENUM('GOALKEEPER', 'RIGHT_FULLBACK', 'LEFT_FULLBACK', 'CENTER_BACK', 'DEFENDING_MIDFIELDER', 'RIGHT_WINGER', 'LEFT_WINGER', 'STRIKER'),team_id bigint,  primary key(id));

ALTER TABLE players
    ADD CONSTRAINT PLAYER_TEAM_ID_FK
        FOREIGN KEY (team_id) REFERENCES teams(id);