package com.ajotcole.ssp_backend.service;

import com.ajotcole.ssp_backend.domain.GameEntity;
import com.ajotcole.ssp_backend.domain.RoundEntity;

import java.util.List;

public interface GameService {

    GameEntity createGame(Integer playerId, Integer rounds, String winner);

    GameEntity createGameWithRounds(Integer playerId, List<RoundEntity> roundEntities);

    Iterable<GameEntity> findGames(Integer playerId);


}
