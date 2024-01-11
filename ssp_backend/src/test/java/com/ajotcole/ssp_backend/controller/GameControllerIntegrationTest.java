package com.ajotcole.ssp_backend.controller;

import com.ajotcole.ssp_backend.TestDataUtil;
import com.ajotcole.ssp_backend.domain.GameEntity;
import com.ajotcole.ssp_backend.domain.PlayerEntity;
import com.ajotcole.ssp_backend.domain.RoundEntity;
import com.ajotcole.ssp_backend.service.GameService;
import com.ajotcole.ssp_backend.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    private final PlayerService playerService;
    private final GameService underTest;

    @Autowired
    public GameControllerIntegrationTest(PlayerService playerService, GameService underTest) {
        this.playerService = playerService;
        this.underTest = underTest;
    }


    @Test
    public void testThatGameWithoutRoundsAreCreated() {
        PlayerEntity player = TestDataUtil.createTestPlayerA();
        playerService.createPlayer(player.getName());
        PlayerEntity resultPlayer = playerService.findPlayerByName(player.getName());

        //language=GraphQL
        String mutation = """
                mutation CreateGame($playerId: Int!) {
                createGame(playerId: $playerId) {
                id,
                playerEntity {
                id
                name                
                                }
                date
                
        
                }
                
                }
                """;

        GameEntity resultGame = graphQlTester.document(mutation)
                .variable("playerId", resultPlayer.getId())
                .execute()
                .path("createGame")
                .entity(GameEntity.class)
                .get();

        assertThat(resultGame).isNotNull();
    }


    //Create game for a user with three rounds


    @Test
    public void testThatCreateGameWithTheRoundsSuccessfully() {
        PlayerEntity player = TestDataUtil.createTestPlayerA();
        playerService.createPlayer(player.getName());
        PlayerEntity resultPlayer = playerService.findPlayerByName(player.getName());

        //language=GraphQL
        String mutation = """
        mutation CreateGameWithRounds ($playerId: Int!) {
          createGameWithRounds(
              playerId: $playerId
              roundEntities: [
                {
                  humanChoice: "paper"
                  computerChoice: "rock"
                  winner: "human"
                },
                {
                  humanChoice: "scissor"
                  computerChoice: "scissor"
                  winner: "tie"
                },
                {
                  humanChoice: "scissor"
                  computerChoice: "stone"
                  winner: "computer"
                }
              ]
          ){
            id
          }
        }
        """;

        GameEntity resultGame = graphQlTester.document(mutation)
                .variable("playerId", resultPlayer.getId())
                .execute()
                .path("createGameWithRounds")
                .entity(GameEntity.class)
                .get();

        assertThat(resultGame).isNotNull();
    }

    // Retrieve all games with Data for player joined

    @Test
    public void testThatRetrieveAllGames() {
        PlayerEntity player = TestDataUtil.createTestPlayerA();
        PlayerEntity resultPlayer = playerService.createPlayer(player.getName());
        List<RoundEntity> roundEntities = TestDataUtil.createThreeTestRounds();
        underTest.createGameWithRounds(resultPlayer.getId().intValue(), roundEntities);

        //language=GraphQL
        String query = """
                query {
                    findGames {
                    id
                    date
                    playerEntity {
                        name
                        id      
                              }
                    rounds
                    winner
                    }
                }
                """;

        List<GameEntity> resultGames = graphQlTester.document(query)
                .execute()
                .path("findGames")
                .entityList(GameEntity.class)
                .get();

        assertThat(resultGames).hasSize(1);
    }
}
