package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.TestDataUtil;
import com.ajotcole.ssp_backend.domain.Game;
import com.ajotcole.ssp_backend.domain.Player;
import com.ajotcole.ssp_backend.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
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
public class GameControllerIntegrationTest {
    @Autowired
    GraphQlTester graphQlTester;

    private final PlayerRepository playerRepository;

    @Autowired
    public GameControllerIntegrationTest(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Test
    public void testThatMultiplePlayersCanBeCreatedAndAllRecalled() {
        Player playerA = TestDataUtil.createTestPlayerA();
        playerRepository.save(playerA);

        Player playerB = TestDataUtil.createTestPlayerB();
        playerRepository.save(playerB);

        Player playerC = TestDataUtil.createTestPlayerC();
        playerRepository.save(playerC);

        //language=GraphQL
        String query = """
        query findAllPlayers {
            findAllPlayers {
                id,
                name
            }
        }
        """;

        List<Player> results = graphQlTester.document(query)
                .execute()
                .path("findAllPlayers")
                .entityList(Player.class)
                .get();

        assertThat(results).hasSize(3);

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

        List<Player> results = graphQlTester.document(query)
                .execute()
                .path("findAllPlayers")
                .entityList(Player.class)
                .get();

        assertThat(results).hasSize(1);
    }

    //Create game for a user with three rounds

    @Test
//    public void testThatGameWithThreeRoundsBeenCreated() {
//        Player player = TestDataUtil.createTestPlayerA();
//        playerRepository.save(player);
//
//        //language=GraphQL
//        String mutation = """
//                mutation createGame {
//                createGame(game: {
//                player_id: 1
//                rounds: [
//                    {
//                        human_choice: "scissor"
//                        computer_choice: "rock"
//                        winner: "computer"
//                    },
//                    {
//                        human_choice: "rock"
//                        computer_choice: "rock"
//                        winner: "tie"
//                    },
//                    {
//                        human_choice: "scissor"
//                        computer_choice: "paper"
//                        winner: "human"
//                    }
//                ]
//
//                }) {
//                          id,
//                          player_id,
//                          rounds {
//                              id
//                              human_choice
//                              computer_choice
//                              winner
//                          }
//                                }
//                }
//                """;
//
//
//        //TODO could be improved to also create rounds and handle integers
//        //Map<String, Object> variables = new HashMap<>();
//        //variables.put("playerId", resultPlayer.getId());
//
//        //String mutationWithData = insertVariables(mutation, variables);
//
//        graphQlTester.document(mutation)
//                .execute()
//                .path("createGame");
//
//        //language=GraphQL
//        String query = """
//            query findGames {
//                findGames {
//                id
//                player_id
//                rounds {
//                    id
//                    human_choice
//                    computer_choice
//                    winner
//                            }
//                                }
//
//            }
//            """;
//
//        List<Game> results = graphQlTester.document(query)
//                .execute()
//                .path("findGames")
//                .entityList(Game.class)
//                .get();
//
//        assertThat(results).isNotNull();
//
//    }

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
