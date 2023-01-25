-- DROP TABLE IF EXISTS public.game_state;
-- CREATE TABLE public.game_state (
--     id serial NOT NULL PRIMARY KEY,
--     current_map text NOT NULL,
--     saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
--     player_id integer NOT NULL
-- );
--
-- DROP TABLE IF EXISTS public.player;
-- CREATE TABLE public.player (
--     id serial NOT NULL PRIMARY KEY,
--     player_name text NOT NULL,
--     hp integer NOT NULL,
--     x integer NOT NULL,
--     y integer NOT NULL
-- );
--
-- ALTER TABLE ONLY public.game_state
--     ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);


ALTER TABLE IF EXISTS ONLY public.player DROP CONSTRAINT IF EXISTS pk_player_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.map DROP CONSTRAINT IF EXISTS pk_map_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.map DROP CONSTRAINT IF EXISTS fk_map_player_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cell DROP CONSTRAINT IF EXISTS pk_cell_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.cell DROP CONSTRAINT IF EXISTS fk_cell_map_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.actor DROP CONSTRAINT IF EXISTS pk_actor_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.actor DROP CONSTRAINT IF EXISTS fk_actor_id CASCADE;


DROP TABLE IF EXISTS public.player;
CREATE TABLE player (
                        id SERIAL NOT NULL,
                        cell_id integer,
                        name VARCHAR(20),
                        health integer,
                        damage integer,
                        items VARCHAR(20)
);
DROP TABLE IF EXISTS public.map;
CREATE TABLE map (
                     id serial NOT NULL,
                     player_id integer,
                     level integer,
                     width integer,
                     height integer
);
DROP TABLE IF EXISTS public.cell;
CREATE TABLE cell (
                      id SERIAL NOT NULL,
                      map_id integer,
                      x integer,
                      y integer,
                      type VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS public.actor;
CREATE TABLE actor (
                       id serial NOT NULL,
                       cell_id integer,
                       health integer
);

ALTER TABLE ONLY map
    ADD CONSTRAINT pk_map_id PRIMARY KEY (id);
ALTER TABLE ONLY cell
    ADD CONSTRAINT pk_cell_id PRIMARY KEY (id);
ALTER TABLE ONLY actor
    ADD CONSTRAINT pk_actor_id PRIMARY KEY (id);
ALTER TABLE ONLY player
    ADD CONSTRAINT pk_player_id PRIMARY KEY (id);

ALTER TABLE ONLY map
    ADD CONSTRAINT fk_map_player_id FOREIGN KEY (player_id) REFERENCES player(id);

ALTER TABLE ONLY cell
    ADD CONSTRAINT fk_cell_map_id FOREIGN KEY (map_id) REFERENCES map(id);

ALTER TABLE ONLY actor
    ADD CONSTRAINT fk_actor_cell_id FOREIGN KEY (cell_id) REFERENCES cell(id);

ALTER TABLE ONLY player
    ADD CONSTRAINT fk_player_cell_id FOREIGN KEY (cell_id) REFERENCES cell(id);
