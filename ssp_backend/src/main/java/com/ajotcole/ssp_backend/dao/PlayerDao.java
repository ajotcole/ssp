package com.ajotcole.ssp_backend.dao;

import com.ajotcole.ssp_backend.domain.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDao {
    Player create(String name);

    Optional<Player> findOne(long l);
    List<Player> findAll();
}
