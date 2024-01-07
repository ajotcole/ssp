package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.TestDataUtil;
import com.ajotcole.ssp_backend.dao.PlayerDao;
import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureGraphQlTester
public class GameControllerIntegrationTest {
    @Autowired
    GraphQlTester graphQlTester;

    private final PlayerDao playerDao;

    @Autowired
    public GameControllerIntegrationTest(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Test
    public void testThatMultiplePlayersCanBeCreatedAndAllRecalled() {
        Player playerA = TestDataUtil.createTestPlayerA();
        playerDao.create(playerA.getName());

        Player playerB = TestDataUtil.createTestPlayerB();
        playerDao.create(playerB.getName());

        Player playerC = TestDataUtil.createTestPlayerC();
        playerDao.create(playerC.getName());

        //language=GraphQL
        String query = """
        query findAllPlayers {
            findAllPlayers {
                id,
                name
            }
        }
        """;

        graphQlTester.document(query)
                .execute()
                .path("findAllPlayers")
                .entityList(Player.class)
                .hasSize(3);

    }

    @Test
    public void testThatPlayerIsCreatedViaGraphQLandCanBeRetrieved() {
        Player playerA = TestDataUtil.createTestPlayerA();

        //language=GraphQL
        String mutation = """
        mutation createPlayer {
            createPlayer(name: $name) {
                id,
                name
            }
        }
        """;

        Map<String, Object> variables = new HashMap<>();
        variables.put("name", playerA.getName());

        String mutationWithData = insertVariables(mutation, variables);

        graphQlTester.document(mutationWithData)
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

        graphQlTester.document(query)
                .execute()
                .path("findAllPlayers")
                .entityList(Player.class)
                .hasSize(1);

    }

    //Create game for a user with three rounds

    @Test
    public void testThatGameWithThreeRoundsBeenCreated() {
        Player player = TestDataUtil.createTestPlayerA();

        Game game = TestDataUtil.createTestGame();

        //language=GraphQL
        String mutation = """
                mutation createGame {
                createGame(game: {
                player_id: $playerId
                rounds: [
                    {
                        human_choice: $round1_humanchoice
                        computer_choice: $round1_computerchoice
                        winner: $round1_winner
                    },
                    {
                        human_choice: $round2_humanchoice
                        computer_choice: $round2_computerchoice
                        winner: $round2_winner
                    },
                    {
                        human_choice: $round3_humanchoice
                        computer_choice: $round3_computerchoice
                        winner: $round3_winner
                    }
                ]
                
                }) {
                          id,
                          player_id,
                          rounds {
                              id
                              human_choice
                              computer_choice
                              winner
                          }
                                }
                }
                """;


        Map<String, Object> variables = new HashMap<>();
        variables.put("player_id", player.getId());



        String mutationWithData = insertVariables(mutation, variables);


        graphQlTester.document(mutationWithData)
                .execute()
                .path("createGame");
    }

    // Retrieve all games with Data for player joined

    // Optional retrieve games with filter


    private static String insertVariables(String query, Map<String, Object> variables) {
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String variableName = entry.getKey();
            Object variableValue = entry.getValue();

            // Replace the variable placeholder in the query with the actual value
            query = query.replace("$" + variableName,'"' + variableValue.toString() + '"');
        }

        return query;
    }
}
