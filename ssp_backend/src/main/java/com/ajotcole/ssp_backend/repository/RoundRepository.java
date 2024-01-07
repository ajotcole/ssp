package com.ajotcole.ssp_backend.repository;

import com.ajotcole.ssp_backend.domain.Round;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends CrudRepository<Round, Long> {
}
