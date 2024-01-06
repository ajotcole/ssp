package com.ajotcole.ssp_backend.dao.impl;

import com.ajotcole.ssp_backend.dao.GameDao;
import com.ajotcole.ssp_backend.domain.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GameDaoImpl implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public GameDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Game game) {
        jdbcTemplate.update(
                "INSERT INTO games (id, date, player_id) VALUES (?, ?, ?)",
                game.getId(),
                game.getDate(),
                game.getPlayerId());
    }

    @Override
    public Optional<Game> findOne(long gameId) {
        List<Game> results = jdbcTemplate.query("SELECT id, date, player_id FROM games WHERE id = ? LIMIT 1",
                new GameDaoImpl.GameRowMapper(),
                gameId);

        return results.stream().findFirst();
    }


    public static class GameRowMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Game.builder()
                    .id(rs.getLong("id"))
                    .playerId(rs.getLong("player_id"))
                    .date(rs.getDate("date").toLocalDate())
                    .build();
        }
    }
}
