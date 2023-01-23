DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS map;
DROP TABLE IF EXISTS mob;
DROP TABLE IF EXISTS cell;

CREATE TABLE game (
                     id serial PRIMARY KEY,
                     player_id integer NOT NULL,,
);

CREATE TABLE player (
                        id SERIAL PRIMARY KEY,
                        cell_id INTEGER REFERENCES cell(id),
                        name VARCHAR(20),
                        health integer,
                        damage integer,
                        items VARCHAR(20)
);

CREATE TABLE map (
                     id serial PRIMARY KEY,
                     game_id INTEGER REFERENCES game(id),
                     level integer,
                     width integer,
                     height integer
);

CREATE TABLE mob (
                     id serial PRIMARY KEY,
                     cell_id INTEGER REFERENCES cell(id),
                     health integer
);
CREATE TABLE cell (
                      id SERIAL PRIMARY KEY,
                      map_id INTEGER REFERENCES map(id),
                      x integer,
                      y integer,
                      type VARCHAR(20) NOT NULL
);

ALTER TABLE ONLY game
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES player(id);