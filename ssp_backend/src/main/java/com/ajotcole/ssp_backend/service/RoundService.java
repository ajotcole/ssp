package com.ajotcole.ssp_backend.service;

import com.ajotcole.ssp_backend.domain.RoundEntity;

import java.util.List;

public interface RoundService {

    RoundEntity createSingleRound (Long gameId, RoundEntity roundEntity);
    List<RoundEntity> createMulitpleRounds(Long gameId, List<RoundEntity> roundEntities);

}
