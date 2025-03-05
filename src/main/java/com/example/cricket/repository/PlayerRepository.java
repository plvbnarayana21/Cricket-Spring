package com.example.cricket.repository;

import com.example.cricket.Beans.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByPname(String pname);
    List<Player> findAllById(List<String> ids);

}
