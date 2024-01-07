DROP TABLE IF EXISTS "players";
DROP TABLE IF EXISTS "games";
DROP TABLE IF EXISTS "rounds";

CREATE TABLE "players"(
    "id" int DEFAULT nextval('players_id_seq') NOT NULL,
    "name" text,
    CONSTRAINT "players_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "games"(
    "id" int DEFAULT nextval('games_id_seq') NOT NULL,
    "date" date,
    "player_id" bigint,
    CONSTRAINT "games_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fK_player" FOREIGN KEY(player_id) REFERENCES players(id)
);

CREATE TABLE "rounds"(
    "id" int DEFAULT nextval('rounds_id_seq') NOT NULL,
    "game_id" bigint,
    "human_choice" text,
    "computer_choice" text,
    "winner" text,
    CONSTRAINT "rounds_pk" PRIMARY KEY ("id"),
    CONSTRAINT "fk_game" FOREIGN KEY (game_id) REFERENCES games(id)
);