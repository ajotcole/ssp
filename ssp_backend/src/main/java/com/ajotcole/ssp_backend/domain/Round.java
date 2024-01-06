package com.ajotcole.ssp_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Round {
    private Long id;
    private Long gameId;
    private String humanChoice;
    private String computerChoice;
    private String winner;
}
