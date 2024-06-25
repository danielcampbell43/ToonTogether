package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.PlaylistSong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistSongsRepository extends CrudRepository<PlaylistSong, Integer> {
}
