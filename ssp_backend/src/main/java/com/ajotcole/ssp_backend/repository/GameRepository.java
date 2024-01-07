package com.ajotcole.ssp_backend.repository;

import com.ajotcole.ssp_backend.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Iterable<Game> findAllByPlayerId(Integer playerId);
}
