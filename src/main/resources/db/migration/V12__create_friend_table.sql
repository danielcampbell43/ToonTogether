CREATE TABLE friends (
    PRIMARY KEY (id),
    id INT GENERATED ALWAYS AS IDENTITY,
    sender_id INT NOT NULL,
    recipient_id INT NOT NULL,
    accepted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    CONSTRAINT fk_friend_sender_user FOREIGN KEY(sender_id) REFERENCES users(id),
    CONSTRAINT fk_friend_recipient_user FOREIGN KEY(recipient_id) REFERENCES users(id)
);