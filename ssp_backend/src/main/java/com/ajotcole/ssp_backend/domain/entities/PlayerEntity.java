package com.ajotcole.ssp_backend.domain.entities;

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
@Table(name = "players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "players_id_seq")
    private Long id;

    private String name;

}
