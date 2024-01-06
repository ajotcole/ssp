package com.ajotcole.ssp_backend.dao.impl;

import com.ajotcole.ssp_backend.dao.RoundDao;
import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import com.ajotcole.ssp_backend.domain.Round;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoundDaoImpl implements RoundDao {
    private final JdbcTemplate jdbcTemplate;

    public RoundDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Round round) {
        jdbcTemplate.update(
                "INSERT INTO round (id, game_id, human_choice, computer_choice, winner) VALUES (?, ?, ?, ?, ?)",
                round.getId(),
                round.getGameId(),
                round.getHumanChoice(),
                round.getComputerChoice(),
                round.getWinner()
                );
    }

    @Override
    public Optional<Round> findOne(long roundId) {
        List<Round> results = jdbcTemplate.query("SELECT id, game_id, human_choice, computer_choice, winner FROM rounds WHERE id = ? LIMIT 1",
                new RoundDaoImpl.RoundRowMapper(),
                roundId);

        return results.stream().findFirst();
    }

    public static class RoundRowMapper implements RowMapper<Round> {
        @Override
        public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Round.builder()
                    .id(rs.getLong("id"))
                    .gameId(rs.getLong("game_id"))
                    .humanChoice(rs.getString("human_choice"))
                    .computerChoice(rs.getString("computer_choice"))
                    .winner(rs.getString("winner"))
                    .build();
        }
    }
}
