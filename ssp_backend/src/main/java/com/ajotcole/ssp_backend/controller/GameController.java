package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.domain.GameEntity;
import com.ajotcole.ssp_backend.domain.RoundEntity;
import com.ajotcole.ssp_backend.service.GameService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @MutationMapping
    public GameEntity createGame(@Argument Integer playerId) { return gameService.createGame(playerId, null, null);}

    @MutationMapping
    public GameEntity createGameWithRounds(@Argument Integer playerId, @Argument List<RoundEntity> roundEntities) {
        return gameService.createGameWithRounds(playerId, roundEntities);
    }

    @QueryMapping
    public Iterable<GameEntity> findGames(@Argument Integer playerId) {
        return gameService.findGames(playerId);
    }

}
