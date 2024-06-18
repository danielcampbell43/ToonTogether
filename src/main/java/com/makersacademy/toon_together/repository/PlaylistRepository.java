package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {
    List<Playlist> findByUserUsername(String username);
}
