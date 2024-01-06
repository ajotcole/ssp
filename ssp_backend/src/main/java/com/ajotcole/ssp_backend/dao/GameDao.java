package com.ajotcole.ssp_backend.dao;

import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;

import java.util.Optional;

public interface GameDao {
    void create(Game game);

    Optional<Game> findOne(long l);
}
