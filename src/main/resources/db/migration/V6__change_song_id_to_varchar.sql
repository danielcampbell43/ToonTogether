TRUNCATE TABLE songs CASCADE;

ALTER TABLE playlist_songs
DROP CONSTRAINT fk_playlist_songs_song;

ALTER TABLE playlist_songs
ALTER COLUMN song_id TYPE varchar(512);

ALTER TABLE songs
DROP CONSTRAINT songs_pkey;
ALTER TABLE songs
ALTER COLUMN id TYPE varchar(512);
ALTER TABLE songs
ADD PRIMARY KEY (id);

ALTER TABLE playlist_songs
ADD CONSTRAINT fk_playlist_songs_song FOREIGN KEY(song_id) REFERENCES songs(id) ON DELETE CASCADE;
