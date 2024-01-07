package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.dao.PlayerDao;
import com.ajotcole.ssp_backend.domain.Player;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GameController {

    private final PlayerDao playerDao;

    public GameController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @QueryMapping
    public List<Player> findAllPlayers() {
        return playerDao.findAll();
    }

    @MutationMapping
    public Player createPlayer(@Argument String name) { return playerDao.create(name); }

}
