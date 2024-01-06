package com.ajotcole.ssp_backend.dao;

import com.ajotcole.ssp_backend.domain.Player;
import com.ajotcole.ssp_backend.domain.Round;

import java.util.Optional;

public interface RoundDao {
    void create(Round round);

    Optional<Round> findOne(long l);
}
