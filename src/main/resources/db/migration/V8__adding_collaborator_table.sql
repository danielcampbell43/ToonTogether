CREATE TABLE collaborators (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    playlist_id BIGINT NOT NULL,
    deleter BOOLEAN NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    CONSTRAINTS fk_collaborators_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINTS fk_collaborators_playlist FOREIGN KEY (playlist_id) REFERENCES playlists(id)
);
