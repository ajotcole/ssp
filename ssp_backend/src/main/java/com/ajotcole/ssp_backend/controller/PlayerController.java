package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.domain.PlayerEntity;
import com.ajotcole.ssp_backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public Iterable<PlayerEntity> findAllPlayers() {
        return playerService.findAllPlayers();
    }

    @MutationMapping
    public PlayerEntity createPlayer(@Argument String name) { return playerService.createPlayer(name);}



}
