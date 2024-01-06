package com.ajotcole.ssp_backend.dao.impl;

import com.ajotcole.ssp_backend.dao.impl.PlayerDaoImpl;
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
        Player player = Player.builder()
                .id(5L)
                .name("Mister Foo")
                .build();

        underTest.create(player);

        //Since nothing is returned, using mockito verify
        verify(jdbcTemplate).update(
                eq("INSERT INTO players (id, name) VALUES (?, ?)"),
                eq(5L), eq("Mister Foo")
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
}
