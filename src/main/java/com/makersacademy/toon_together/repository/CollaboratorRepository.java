package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Collaborator;
import com.makersacademy.toon_together.model.User;
import com.makersacademy.toon_together.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

    boolean existsByUserAndPlaylist(User user, Playlist playlist);

    List<Collaborator> findByPlaylist(Playlist playlist);

}
