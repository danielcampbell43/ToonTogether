package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Playlist;
import com.makersacademy.toon_together.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {
    List<Playlist> findByOwner(User user);
    Playlist findById(int id);
    void deleteById(int id);
    Page<Playlist> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Playlist> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
