package com.ajotcole.ssp_backend;

import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import com.ajotcole.ssp_backend.domain.Round;

import java.time.LocalDate;
import java.util.List;

public class TestDataUtil {

    public static Player createTestPlayerA() {
        return Player.builder()
                .name("Mister Foo")
                .build();
    }

    public static Player createTestPlayerB() {
        return Player.builder()
                .name("Mister Bar")
                .build();
    }

    public static Player createTestPlayerC() {
        return Player.builder()
                .name("Mister Baz")
                .build();
    }

    public static Game createTestGame(Player player) {
        return Game.builder()
                .date(LocalDate.of(2024, 1, 6))
                .player(player)
                .build();
    }

    public static Round createTestRound(Game game) {
        return Round.builder()
                .game(game)
                .humanChoice("scissor")
                .computerChoice("rock")
                .winner("computer")
                .build();
    }

    public static List<Round> createThreeTestRounds(Game game) {
        return List.of(
                Round.builder()
                        .game(game)
                        .humanChoice("scissor")
                        .computerChoice("rock")
                        .winner("computer")
                        .build(),
                Round.builder()
                        .game(game)
                        .humanChoice("rock")
                        .computerChoice("rock")
                        .winner("tie")
                        .build(),
                Round.builder()
                        .game(game)
                        .humanChoice("scissor")
                        .computerChoice("paper")
                        .winner("human")
                        .build()
        );
    }



}
