CREATE TABLE collaborators (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    playlist_id BIGINT NOT NULL,
    deleter BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_collaborators_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_collaborators_playlist FOREIGN KEY (playlist_id) REFERENCES playlists(id)
);
