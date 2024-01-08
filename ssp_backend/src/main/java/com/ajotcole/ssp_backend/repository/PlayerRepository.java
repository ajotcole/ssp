package com.ajotcole.ssp_backend.repository;

import com.ajotcole.ssp_backend.domain.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {
    PlayerEntity findPlayerByName(String name);
}
