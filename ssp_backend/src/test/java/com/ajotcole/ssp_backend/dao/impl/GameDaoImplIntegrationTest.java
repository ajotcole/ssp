package com.ajotcole.ssp_backend.dao.impl;

import com.ajotcole.ssp_backend.TestDataUtil;
import com.ajotcole.ssp_backend.dao.PlayerDao;
import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GameDaoImplIntegrationTest {
    private final PlayerDao playerDao;
    private final GameDaoImpl underTest;

    @Autowired
    public GameDaoImplIntegrationTest(GameDaoImpl underTest, PlayerDao playerDao) {
        this.underTest = underTest;
        this.playerDao = playerDao;
    }

    @Test
    public void testThatGameCanBeCreatedAndRecalled() {
        Player player = TestDataUtil.createTestPlayerA();
        playerDao.create(player.getName());
        Game game = TestDataUtil.createTestGame();
        game.setPlayerId(player.getId());
        underTest.create(game);
        Optional<Game> result = underTest.findOne(game.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(game);
    }


}
