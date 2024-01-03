package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.model.Game;
import com.ajotcole.ssp_backend.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GameController {

    Logger logger = LoggerFactory.getLogger(GameController.class);
    @Autowired
    private GameRepository gameRepository;

    @QueryMapping
    public List<Game> listAllGames() {
        return List.of();
    }

    @MutationMapping
    public void saveGame() {
        return;
    }
}
