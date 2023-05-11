
CREATE TABLE IF NOT EXISTS actor (
    id              int         PRIMARY KEY,
    name            text        NOT NULL,
    info            text        NOT NULL,
    birth_date      timestamp   NOT NULL
);

CREATE TABLE IF NOT EXISTS film (
    id              int         PRIMARY KEY,
    name            text        NOT NULL,
    description     text        NOT NULL,
    director        text        NOT NULL,
    rating          float       NOT NULL,
    users_rated     int         NOT NULL,
    box_office      int         NOT NULL
);

CREATE TABLE IF NOT EXISTS actor_film (
    id              int         PRIMARY KEY,
    actor_id        int         REFERENCES actor(id),
    film_id         int         REFERENCES film(id)
);

CREATE TABLE IF NOT EXISTS users (
    id              int         PRIMARY KEY,
    name            text        NOT NULL,
    email           text        NOT NULL,
    password_hash   text        NOT NULL
);

CREATE TABLE IF NOT EXISTS review (
    id              int         PRIMARY KEY,
    user_id         int         REFERENCES users(id),
    film_id         int         REFERENCES film(id),
    title           text        NOT NULL,
    text            text        NOT NULL
);
