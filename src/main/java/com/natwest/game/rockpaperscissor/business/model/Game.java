package com.natwest.game.rockpaperscissor.business.model;

import com.natwest.game.rockpaperscissor.business.enums.GameStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    private GameStatus gameStatus;

    private String playerOneName;

    private Integer playerOneScore;

    private String playerTwoName;

    private Integer playerTwoScore;

    @OneToMany
    private List<Round> rounds;

    public Game(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
        this.rounds = new ArrayList<>();
    }
}