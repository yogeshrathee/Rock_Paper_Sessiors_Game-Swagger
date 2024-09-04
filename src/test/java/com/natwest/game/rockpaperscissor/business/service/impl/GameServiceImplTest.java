package com.natwest.game.rockpaperscissor.business.service.impl;

import com.natwest.game.rockpaperscissor.business.dao.GameDAO;
import com.natwest.game.rockpaperscissor.business.dao.RoundDAO;
import com.natwest.game.rockpaperscissor.business.enums.Choice;
import com.natwest.game.rockpaperscissor.business.enums.GameStatus;
import com.natwest.game.rockpaperscissor.business.exception.GameNotFoundException;
import com.natwest.game.rockpaperscissor.business.exception.GameOverException;
import com.natwest.game.rockpaperscissor.business.model.Game;
import com.natwest.game.rockpaperscissor.business.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceImplTest {

    private GameDAO gameDAO;
    private RoundDAO roundDAO;
    private GameService classUnderTest;

    @BeforeEach
    public void setup() {
        gameDAO = mock(GameDAO.class);
        roundDAO = mock(RoundDAO.class);
        classUnderTest = new GameServiceImpl(gameDAO, roundDAO);
    }

    @Test
    public void shouldThrowGameNotFoundException() {
        // given
        when(gameDAO.findById(any())).thenReturn(Optional.empty());

        // when & then
        assertThrows(GameNotFoundException.class, () -> {
            classUnderTest.getStatus(1L);
        });
    }

    @Test
    public void shouldThrowGameOverException() throws GameNotFoundException {
        // given
        Game givenGame = new Game();
        givenGame.setGameStatus(GameStatus.FINISHED);
        Long givenId = Long.valueOf(1L);
        when(gameDAO.findById(any())).thenReturn(Optional.of(givenGame));

        // when & then
        assertThrows(GameOverException.class, () -> {
            classUnderTest.play(givenId, Choice.getRandom(), Choice.getRandom());
        });
    }
}
