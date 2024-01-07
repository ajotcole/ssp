package com.ajotcole.ssp_backend.dao.impl;

import com.ajotcole.ssp_backend.dao.GameDao;
import com.ajotcole.ssp_backend.domain.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class GameDaoImpl implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public GameDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Game create(Game game) {
        Random random = new Random();
        int id = random.nextInt();


        jdbcTemplate.update(
                "INSERT INTO games (id, date, player_id) VALUES (?, ?, ?)",
                id,
                game.getDate(),
                game.getPlayerId());
        return null;
    }

    @Override
    public Optional<Game> findOne(long gameId) {
        List<Game> results = jdbcTemplate.query("SELECT id, date, player_id FROM games WHERE id = ? LIMIT 1",
                new GameDaoImpl.GameRowMapper(),
                gameId);

        return results.stream().findFirst();
    }

    @Override
    public List<Game> findMany() {
        return jdbcTemplate.query("SELECT id, date, player_id FROM games",
                new GameDaoImpl.GameRowMapper());
    }


    public static class GameRowMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Game.builder()
                    .id(rs.getInt("id"))
                    .playerId(rs.getInt("player_id"))
                    .date(rs.getDate("date").toLocalDate())
                    .build();
        }
    }
}
