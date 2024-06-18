ALTER TABLE playlist_songs
DROP CONSTRAINT fk_playlist_songs_playlist;

ALTER TABLE playlist_songs
DROP CONSTRAINT fk_playlist_songs_song;

ALTER TABLE playlist_songs
ADD CONSTRAINT fk_playlist_songs_playlist FOREIGN KEY(playlist_id) REFERENCES playlists(id) ON DELETE CASCADE;

ALTER TABLE playlist_songs
ADD CONSTRAINT fk_playlist_songs_song FOREIGN KEY(song_id) REFERENCES songs(id) ON DELETE CASCADE;