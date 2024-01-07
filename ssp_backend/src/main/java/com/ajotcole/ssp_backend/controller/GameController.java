package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import com.ajotcole.ssp_backend.repository.GameRepository;
import com.ajotcole.ssp_backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GameController {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Autowired
    public GameController(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @QueryMapping
    public Iterable<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @MutationMapping
    public Player createPlayer(@Argument String name) { return playerRepository.save(Player.builder().name(name).build());}

    @MutationMapping
    public Game createGame (@Argument Game game) { return gameRepository.save(game);}

    @QueryMapping
    public Iterable<Game> findGames(@Argument Integer playerId) { return gameRepository.findAllByPlayerId(playerId);}

}
