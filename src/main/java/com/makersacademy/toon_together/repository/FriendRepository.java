package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Friend;
import com.makersacademy.toon_together.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    List<Friend> findAllBySender(User sender);
    List<Friend> findAllByRecipient(User recipient);
    Optional<Friend> findBySenderAndRecipient(User sender, User recipient);
}
