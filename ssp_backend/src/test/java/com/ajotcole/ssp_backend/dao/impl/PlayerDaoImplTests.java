package com.ajotcole.ssp_backend.dao.impl;

import com.ajotcole.ssp_backend.TestDataUtil;
import com.ajotcole.ssp_backend.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PlayerDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PlayerDaoImpl underTest;

    @Test
    public void createPlayerWithCorrectSql() {
        Player player = TestDataUtil.createTestPlayerA();

        underTest.create(player.getName());

        //Since nothing is returned, using mockito verify
        verify(jdbcTemplate).update(
                eq("INSERT INTO players (name) VALUES (?)"),
                eq("Mister Foo")
        );
    }

    @Test
    public void findOnePlayerWithCorrectSql() {
        underTest.findOne(5L);
        verify(jdbcTemplate).query(
                eq("SELECT id, name FROM players WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<PlayerDaoImpl.PlayerRowMapper>any(),
                eq(5L)
        );
    }

    @Test
    public void findAllPlayersWithCorrectSql() {
        underTest.findAll();
        verify(jdbcTemplate).query((
                eq("SELECT id, name FROM players")),
                ArgumentMatchers.<PlayerDaoImpl.PlayerRowMapper>any()
                );
    }
}
