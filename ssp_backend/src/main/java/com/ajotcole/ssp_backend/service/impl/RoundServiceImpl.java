package com.ajotcole.ssp_backend.service.impl;

import com.ajotcole.ssp_backend.domain.RoundEntity;
import com.ajotcole.ssp_backend.service.RoundService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundServiceImpl implements RoundService {
    @Override
    public RoundEntity createSingleRound(Long gameId, RoundEntity roundEntity) {
        return null;
    }

    @Override
    public List<RoundEntity> createMulitpleRounds(Long gameId, List<RoundEntity> roundEntities) {
        return null;
    }
}
