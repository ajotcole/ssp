package com.ajotcole.ssp_backend;

import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import com.ajotcole.ssp_backend.domain.Round;

import java.time.LocalDate;
import java.util.List;

public class TestDataUtil {

    public static Player createTestPlayerA() {
        return Player.builder()
                .id(5)
                .name("Mister Foo")
                .build();
    }

    public static Player createTestPlayerB() {
        return Player.builder()
                .id(123)
                .name("Mister Bar")
                .build();
    }

    public static Player createTestPlayerC() {
        return Player.builder()
                .id(987)
                .name("Mister Baz")
                .build();
    }

    public static Game createTestGame() {
        return Game.builder()
                .id(4)
                .date(LocalDate.of(2024, 1, 6))
                .playerId(85L)
                .build();
    }

    public static Round createTestRound() {
        return Round.builder()
                .id(123)
                .gameId(35)
                .humanChoice("scissor")
                .computerChoice("rock")
                .winner("computer")
                .build();
    }

    public static List<Round> createThreeTestRounds(Integer gameId) {
        return List.of(
                Round.builder()
                        .gameId(gameId)
                        .humanChoice("scissor")
                        .computerChoice("rock")
                        .winner("computer")
                        .build(),
                Round.builder()
                        .gameId(gameId)
                        .humanChoice("rock")
                        .computerChoice("rock")
                        .winner("tie")
                        .build(),
                Round.builder()
                        .gameId(gameId)
                        .humanChoice("scissor")
                        .computerChoice("paper")
                        .winner("human")
                        .build()
        );
    }



}
