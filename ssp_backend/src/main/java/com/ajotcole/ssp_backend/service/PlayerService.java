package com.ajotcole.ssp_backend.service;

import com.ajotcole.ssp_backend.domain.entities.PlayerEntity;

public interface PlayerService {

    PlayerEntity createPlayer (String name);

    Iterable<PlayerEntity> findAllPlayers();

}
