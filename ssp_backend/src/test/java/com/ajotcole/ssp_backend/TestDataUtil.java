package com.ajotcole.ssp_backend;

import com.ajotcole.ssp_backend.domain.entities.GameEntity;
import com.ajotcole.ssp_backend.domain.entities.PlayerEntity;
import com.ajotcole.ssp_backend.domain.entities.RoundEntity;

import java.time.LocalDate;
import java.util.List;

public class TestDataUtil {

    public static PlayerEntity createTestPlayerA() {
        return PlayerEntity.builder()
                .name("Mister Foo")
                .build();
    }

    public static PlayerEntity createTestPlayerB() {
        return PlayerEntity.builder()
                .name("Mister Bar")
                .build();
    }

    public static PlayerEntity createTestPlayerC() {
        return PlayerEntity.builder()
                .name("Mister Baz")
                .build();
    }

    public static GameEntity createTestGame(PlayerEntity playerEntity) {
        return GameEntity.builder()
                .date(LocalDate.of(2024, 1, 6))
                .playerEntity(playerEntity)
                .build();
    }

    public static RoundEntity createTestRound(GameEntity gameEntity) {
        return RoundEntity.builder()
                .gameEntity(gameEntity)
                .humanChoice("scissor")
                .computerChoice("rock")
                .winner("computer")
                .build();
    }

    public static List<RoundEntity> createThreeTestRounds(GameEntity gameEntity) {
        return List.of(
                RoundEntity.builder()
                        .gameEntity(gameEntity)
                        .humanChoice("scissor")
                        .computerChoice("rock")
                        .winner("computer")
                        .build(),
                RoundEntity.builder()
                        .gameEntity(gameEntity)
                        .humanChoice("rock")
                        .computerChoice("rock")
                        .winner("tie")
                        .build(),
                RoundEntity.builder()
                        .gameEntity(gameEntity)
                        .humanChoice("scissor")
                        .computerChoice("paper")
                        .winner("human")
                        .build()
        );
    }



}
