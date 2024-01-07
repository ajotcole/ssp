package com.ajotcole.ssp_backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "games_id_seq")
    private Long id;

    private LocalDate date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;

    private Integer rounds;
    private String winner;
}
