DROP TABLE IF EXISTS users;

CREATE TABLE users (
    PRIMARY KEY (id),
    id INT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(25) NOT NULL,
    enabled BOOLEAN NOT NULL,
    profile_picture VARCHAR(100),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);

CREATE TABLE authorities (
    PRIMARY KEY (id),
    id INT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username),
    authority VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);

CREATE UNIQUE index ix_auth_username ON authorities(username, authority);

CREATE TABLE playlists (
    PRIMARY KEY (id),
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(256) NOT NULL,
    owner INT NOT NULL,
    CONSTRAINT fk_playlist_owner FOREIGN KEY(owner) REFERENCES users(id),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);

CREATE TABLE songs (
    PRIMARY KEY (id),
    id INT,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);

CREATE TABLE playlist_songs (
    PRIMARY KEY (id),
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    playlist_id INT NOT NULL,
    CONSTRAINT fk_playlist_songs_playlist FOREIGN KEY(playlist_id) REFERENCES playlists(id),
    song_id INT NOT NULL,
    CONSTRAINT fk_playlist_songs_song FOREIGN KEY(song_id) REFERENCES songs(id)
);