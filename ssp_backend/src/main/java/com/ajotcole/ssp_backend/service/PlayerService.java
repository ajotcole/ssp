package com.ajotcole.ssp_backend.service;

import com.ajotcole.ssp_backend.domain.PlayerEntity;

public interface PlayerService {

    PlayerEntity createPlayer (String name);

    PlayerEntity findPlayerByName (String name);

    Iterable<PlayerEntity> findAllPlayers();

}
