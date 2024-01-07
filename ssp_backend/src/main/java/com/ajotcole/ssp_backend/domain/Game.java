package com.ajotcole.ssp_backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "games_id_seq")
    private Long id;

    private LocalDate date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rounds")
    private List<Round> rounds;
}
