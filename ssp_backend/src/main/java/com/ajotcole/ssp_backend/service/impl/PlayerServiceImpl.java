package com.ajotcole.ssp_backend.service.impl;

import com.ajotcole.ssp_backend.domain.PlayerEntity;
import com.ajotcole.ssp_backend.repository.PlayerRepository;
import com.ajotcole.ssp_backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerEntity createPlayer(String name) {

        PlayerEntity player = PlayerEntity.builder().name(name).build();

        return playerRepository.save(player);
    }

    @Override
    public PlayerEntity findPlayerByName(String name) {
        return playerRepository.findPlayerByName(name);
    }

    @Override
    public Iterable<PlayerEntity> findAllPlayers() {
        return playerRepository.findAll();
    }
}
