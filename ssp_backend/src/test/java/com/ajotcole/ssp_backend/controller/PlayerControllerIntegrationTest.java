package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.TestDataUtil;
import com.ajotcole.ssp_backend.domain.PlayerEntity;
import com.ajotcole.ssp_backend.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureGraphQlTester
public class PlayerControllerIntegrationTest {

    @Autowired
    GraphQlTester graphQlTester;

    private final PlayerServiceImpl playerService;

    @Autowired
    public PlayerControllerIntegrationTest(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }

    @Test
    public void testThatMultiplePlayersCanBeCreatedAndAllRecalled() {
        PlayerEntity playerEntityA = TestDataUtil.createTestPlayerA();
        playerService.createPlayer(playerEntityA.getName());

        PlayerEntity playerEntityB = TestDataUtil.createTestPlayerB();
        playerService.createPlayer(playerEntityB.getName());

        PlayerEntity playerEntityC = TestDataUtil.createTestPlayerC();
        playerService.createPlayer(playerEntityC.getName());

        //language=GraphQL
        String query = """
        query findAllPlayers {
            findAllPlayers {
                id,
                name
            }
        }
        """;

        List<PlayerEntity> results = graphQlTester.document(query)
                .execute()
                .path("findAllPlayers")
                .entityList(PlayerEntity.class)
                .get();

        assertThat(results).hasSize(3);
    }

    @Test
    public void testThatPlayerIsCreatedViaGraphQLandCanBeRetrieved() {
        PlayerEntity playerEntityA = TestDataUtil.createTestPlayerA();

        //language=GraphQL
        String mutation = """
        mutation createPlayer($name: String!) {
            createPlayer(name: $name) {
                id,
                name
            }
        }
        """;

        graphQlTester.document(mutation)
                .variable("name", playerEntityA.getName())
                .execute()
                .path("createPlayer");

        //language=GraphQL
        String query = """
        query findAllPlayers {
            findAllPlayers {
                id,
                name
            }
        }
        """;

        List<PlayerEntity> results = graphQlTester.document(query)
                .execute()
                .path("findAllPlayers")
                .entityList(PlayerEntity.class)
                .get();

        assertThat(results).hasSize(1);
    }
}
