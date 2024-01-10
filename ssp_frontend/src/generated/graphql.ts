import { gql } from 'apollo-angular';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
};

export type Game = {
  __typename?: 'Game';
  id?: Maybe<Scalars['Int']['output']>;
  playerId?: Maybe<Scalars['Int']['output']>;
  rounds?: Maybe<Scalars['Int']['output']>;
  winner?: Maybe<Scalars['String']['output']>;
};

export type Mutation = {
  __typename?: 'Mutation';
  createGame?: Maybe<Game>;
  createGameWithRounds?: Maybe<Game>;
  createPlayer?: Maybe<Player>;
};


export type MutationCreateGameArgs = {
  playerId: Scalars['Int']['input'];
};


export type MutationCreateGameWithRoundsArgs = {
  playerId: Scalars['Int']['input'];
  roundEntities: Array<InputMaybe<RoundInput>>;
};


export type MutationCreatePlayerArgs = {
  name: Scalars['String']['input'];
};

export type Player = {
  __typename?: 'Player';
  id?: Maybe<Scalars['Int']['output']>;
  name?: Maybe<Scalars['String']['output']>;
};

export type Query = {
  __typename?: 'Query';
  findAllPlayers?: Maybe<Array<Maybe<Player>>>;
  findGames?: Maybe<Array<Maybe<Game>>>;
};


export type QueryFindGamesArgs = {
  playerId?: InputMaybe<Scalars['Int']['input']>;
};

export type Round = {
  __typename?: 'Round';
  computerChoice?: Maybe<Scalars['String']['output']>;
  humanChoice?: Maybe<Scalars['String']['output']>;
  id?: Maybe<Scalars['Int']['output']>;
  winner?: Maybe<Scalars['String']['output']>;
};

export type RoundInput = {
  computerChoice?: InputMaybe<Scalars['String']['input']>;
  humanChoice?: InputMaybe<Scalars['String']['input']>;
  winner?: InputMaybe<Scalars['String']['input']>;
};
