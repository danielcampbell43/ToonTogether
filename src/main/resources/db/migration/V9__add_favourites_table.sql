CREATE TABLE favourites (
    PRIMARY KEY (id),
    id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL,
    CONSTRAINT fk_favourites_user FOREIGN KEY(user_id) REFERENCES users(id),
    playlist_id INT NOT NULL,
    CONSTRAINT fk_favourites_playlist FOREIGN KEY(playlist_id) REFERENCES playlists(id),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);