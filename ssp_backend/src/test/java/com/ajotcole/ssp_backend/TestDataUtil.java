package com.ajotcole.ssp_backend;

import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import com.ajotcole.ssp_backend.domain.Round;

import java.time.LocalDate;

public class TestDataUtil {

    public static Player createTestPlayerA() {
        return Player.builder()
                .id(5L)
                .name("Mister Foo")
                .build();
    }

    public static Player createTestPlayerB() {
        return Player.builder()
                .id(123L)
                .name("Mister Bar")
                .build();
    }

    public static Player createTestPlayerC() {
        return Player.builder()
                .id(987L)
                .name("Mister Baz")
                .build();
    }

    public static Game createTestGame() {
        return Game.builder()
                .id(4L)
                .date(LocalDate.of(2024, 1, 6))
                .playerId(85L)
                .build();
    }

    public static Round createTestRound() {
        return Round.builder()
                .id(123L)
                .gameId(35L)
                .humanChoice("scissor")
                .computerChoice("rock")
                .winner("computer")
                .build();
    }



}
