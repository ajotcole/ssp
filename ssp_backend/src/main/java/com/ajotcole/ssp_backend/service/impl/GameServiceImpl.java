package com.ajotcole.ssp_backend.service.impl;

import com.ajotcole.ssp_backend.domain.GameEntity;
import com.ajotcole.ssp_backend.domain.PlayerEntity;
import com.ajotcole.ssp_backend.domain.RoundEntity;
import com.ajotcole.ssp_backend.repository.GameRepository;
import com.ajotcole.ssp_backend.repository.PlayerRepository;
import com.ajotcole.ssp_backend.repository.RoundRepository;
import com.ajotcole.ssp_backend.service.GameService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final RoundRepository roundRepository;

    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, RoundRepository roundRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.roundRepository = roundRepository;
    }

    @Transactional
    public GameEntity createGame(Integer playerId, Integer rounds, String winner) {
        PlayerEntity player = playerRepository.findById(playerId.longValue())
                .orElseThrow(() -> new EntityNotFoundException("Player with id " + playerId + " not found"));

        GameEntity gameEntity = GameEntity.builder()
                .playerEntity(player)
                .rounds(rounds)
                .winner(winner)
                .date(LocalDate.now())
                .build();

        return gameRepository.save(gameEntity);
    }

    @Transactional
    public GameEntity createGameWithRounds(Integer playerId, List<RoundEntity> roundEntities) {
        String gameWinner = determineGameWinner(roundEntities);

        GameEntity game = this.createGame(playerId, roundEntities.size(), gameWinner);
        roundRepository.saveAll(roundEntities).forEach(roundEntity -> roundEntity.setGameEntity(game));
        return game;
    }

    @Override
    public Iterable<GameEntity> findGames(Integer playerId) {
        return gameRepository.findAll();
    }

    private String determineGameWinner(List<RoundEntity> rounds) {
        //TODO could need some refactoring in the future
        List<String> winnerList = rounds.stream().map(RoundEntity::getWinner).sorted().toList();

        // Count occurrences of each winner
        Map<String, Long> winnerCounts = winnerList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Find the winner with the maximum count
        long maxCount = 0;
        String winner = null;

        for (Map.Entry<String, Long> entry : winnerCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                winner = entry.getKey();
            } else if (entry.getValue() == maxCount) {
                // It's a tie if there are multiple winners with the same count
                winner = "tie";
            }
        }

        return winner != null ? winner : "No winner"; // Handle the case when there is no winner
    }
}
