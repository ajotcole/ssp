package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.domain.entities.GameEntity;
import com.ajotcole.ssp_backend.domain.entities.PlayerEntity;
import com.ajotcole.ssp_backend.repository.GameRepository;
import com.ajotcole.ssp_backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {
    private final PlayerService playerService;
    private final GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }

    @QueryMapping
    public Iterable<PlayerEntity> findAllPlayers() {
        return playerService.findAllPlayers();
    }

    @MutationMapping
    public PlayerEntity createPlayer(@Argument String name) { return playerService.createPlayer(name);}

    @MutationMapping
    public GameEntity createGame (@Argument GameEntity gameEntity) { return gameRepository.save(gameEntity);}

    @QueryMapping
    public Iterable<GameEntity> findGames(@Argument Integer playerId) { return gameRepository.findAllByPlayerId(playerId);}

}
