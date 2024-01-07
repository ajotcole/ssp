package com.ajotcole.ssp_backend.repository;

import com.ajotcole.ssp_backend.TestDataUtil;
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
public class PlayerRepositoryIntegrationTest {

    private final PlayerRepository underTest;

    @Autowired
    public PlayerRepositoryIntegrationTest(PlayerRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatGameCanBeCreatedAndRecalled() {
        Player player = TestDataUtil.createTestPlayerA();
        underTest.save(player);
        Optional<Player> result = underTest.findById(player.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(player);
    }


}
