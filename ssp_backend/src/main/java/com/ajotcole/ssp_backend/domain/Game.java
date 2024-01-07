package com.ajotcole.ssp_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    private Integer id;

    private LocalDate date;

    private Integer playerId;
}
