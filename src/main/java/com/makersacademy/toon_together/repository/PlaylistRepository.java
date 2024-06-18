package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {
    Playlist findById(int id);
    void deleteById(int id);
}
