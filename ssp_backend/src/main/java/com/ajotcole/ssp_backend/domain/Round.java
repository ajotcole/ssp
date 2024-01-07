package com.ajotcole.ssp_backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rounds")
public class Round {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "rounds_id_seq")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;
    private String humanChoice;
    private String computerChoice;
    private String winner;
}
