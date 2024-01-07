package com.ajotcole.ssp_backend.dao.impl;

import com.ajotcole.ssp_backend.dao.PlayerDao;
import com.ajotcole.ssp_backend.domain.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class PlayerDaoImpl implements PlayerDao {
    private final JdbcTemplate jdbcTemplate;

    public PlayerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Player create(String name) {

        Random random = new Random();
        int id = random.nextInt();

        jdbcTemplate.update("INSERT INTO players (id, name) VALUES (?, ?)", id, name);
        return null;
    }

    @Override
    public Optional<Player> findOne(long playerId) {
         List<Player> results = jdbcTemplate.query("SELECT id, name FROM players WHERE id = ? LIMIT 1",
                new PlayerRowMapper(),
                playerId);

         return results.stream().findFirst();
    }

    @Override
    public List<Player> findAll() {
        return  jdbcTemplate.query("SELECT id, name FROM players",
                new PlayerRowMapper());
    }

    public static class PlayerRowMapper implements RowMapper<Player> {

        @Override
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Player.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }
}
