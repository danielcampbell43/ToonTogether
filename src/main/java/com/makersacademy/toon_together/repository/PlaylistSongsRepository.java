package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.PlaylistSong;
import com.makersacademy.toon_together.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistSongsRepository extends JpaRepository<PlaylistSong, Long> {
    List<PlaylistSong> findByPlaylistId(Integer playlistId);
    void deleteByPlaylistAndSong(Playlist playlist, Song song);
}
