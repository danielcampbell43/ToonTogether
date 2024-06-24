package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Favourite;
import com.makersacademy.toon_together.model.Playlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavouriteRepository extends CrudRepository<Favourite, Integer> {
    List<Favourite> findFavouriteByUserId(int userId);
    Favourite findFavouriteByUserIdAndPlaylistId(int userId, int playlistId);
}
