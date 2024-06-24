package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Favourite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavouriteRepository extends CrudRepository<Favourite, Integer> {
    Favourite findFavouriteByUserIdAndPlaylistId(int userId, int playlistId);
}
