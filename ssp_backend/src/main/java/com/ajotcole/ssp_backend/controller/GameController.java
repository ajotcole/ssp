package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.dao.GameDao;
import com.ajotcole.ssp_backend.dao.PlayerDao;
import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GameController {

    private final PlayerDao playerDao;
    private final GameDao gameDao;

    public GameController(PlayerDao playerDao, GameDao gameDao) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
    }

    @QueryMapping
    public List<Player> findAllPlayers() {
        return playerDao.findAll();
    }

    @MutationMapping
    public Player createPlayer(@Argument String name) { return playerDao.create(name); }

    @MutationMapping
    public Game createGame (@Argument Game game) { return gameDao.create(game); }

}
