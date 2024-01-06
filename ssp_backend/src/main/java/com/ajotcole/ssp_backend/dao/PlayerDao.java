package com.ajotcole.ssp_backend.dao;

import com.ajotcole.ssp_backend.domain.Player;

import java.util.Optional;

public interface PlayerDao {
    void create(Player player);

    Optional<Player> findOne(long l);
}
