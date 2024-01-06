package com.ajotcole.ssp_backend.dao.impl;

import com.ajotcole.ssp_backend.dao.impl.GameDaoImpl;
import com.ajotcole.ssp_backend.dao.impl.RoundDaoImpl;
import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Round;
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
public class RoundDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RoundDaoImpl underTest;

    @Test
    public void createGameWithCorrectSql() {

        Round round = Round.builder()
                .id(123L)
                .gameId(35L)
                .humanChoice("scissor")
                .computerChoice("rock")
                .winner("computer")
                .build();

        underTest.create(round);

        //Since nothing is returned, using mockito verify
        verify(jdbcTemplate).update(
                eq("INSERT INTO round (id, game_id, human_choice, computer_choice, winner) VALUES (?, ?, ?, ?, ?)"),
                eq(123L), eq(35L), eq("scissor"), eq("rock"), eq("computer")
        );
    }

    @Test
    public void findOneRoundWithCorrectSql() {
        underTest.findOne(4L);
        verify(jdbcTemplate).query(
                eq("SELECT id, game_id, human_choice, computer_choice, winner FROM rounds WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<GameDaoImpl.GameRowMapper>any(),
                eq(4L)
        );
    }

}
