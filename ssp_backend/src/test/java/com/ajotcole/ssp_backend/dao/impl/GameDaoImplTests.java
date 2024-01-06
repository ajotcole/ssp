package com.ajotcole.ssp_backend.dao.impl;

import com.ajotcole.ssp_backend.dao.impl.GameDaoImpl;
import com.ajotcole.ssp_backend.dao.impl.PlayerDaoImpl;
import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private GameDaoImpl underTest;

    @Test
    public void createGameWithCorrectSql() {
        Game game = Game.builder()
                        .id(4L)
                        .date(LocalDate.of(2024, 1, 6))
                        .playerId(85L)
                                .build();


        underTest.create(game);

        //Since nothing is returned, using mockito verify
        verify(jdbcTemplate).update(
                eq("INSERT INTO games (id, date, player_id) VALUES (?, ?, ?)"),
                eq(4L), eq(LocalDate.of(2024, 1, 6)), eq(85L)
        );
    }

    @Test
    public void findOneGameWithCorrectSql() {
        underTest.findOne(4L);
        verify(jdbcTemplate).query(
                eq("SELECT id, date, player_id FROM games WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<GameDaoImpl.GameRowMapper>any(),
                eq(4L)
        );
    }
}
