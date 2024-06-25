package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, String> {
}
