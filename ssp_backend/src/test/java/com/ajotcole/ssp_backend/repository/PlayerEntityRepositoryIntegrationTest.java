package com.ajotcole.ssp_backend.repository;

import com.ajotcole.ssp_backend.TestDataUtil;
import com.ajotcole.ssp_backend.domain.entities.PlayerEntity;
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
public class PlayerEntityRepositoryIntegrationTest {

    private final PlayerRepository underTest;

    @Autowired
    public PlayerEntityRepositoryIntegrationTest(PlayerRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatGameCanBeCreatedAndRecalled() {
        PlayerEntity playerEntity = TestDataUtil.createTestPlayerA();
        underTest.save(playerEntity);
        Optional<PlayerEntity> result = underTest.findById(playerEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(playerEntity);
    }


}
