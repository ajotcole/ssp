package com.ajotcole.ssp_backend.model;

public class GameRound {
    private Integer id;
    private Choice humanChoice;
    private Choice computerChoice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Choice getHumanChoice() {
        return humanChoice;
    }

    public void setHumanChoice(Choice humanChoice) {
        this.humanChoice = humanChoice;
    }

    public Choice getComputerChoice() {
        return computerChoice;
    }

    public void setComputerChoice(Choice computerChoice) {
        this.computerChoice = computerChoice;
    }
}
