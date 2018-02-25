package com.game.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.game.dto.ContestDTO;
import com.game.dto.ResultsDTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceTest {
	
	@Mock
	ChallengeService challengeServiceMock;
	
	@InjectMocks
	GameServiceImpl gameServiceImplMock; 
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddNewPlayertoTheGameAndReturns() {
		
		String playerNickname = "Joe";
		gameServiceImplMock.addNewPlayerToTheGame(playerNickname);
		
		assertEquals(playerNickname, gameServiceImplMock.getPlayersList().get(0));
		
	}
	
	@Test
	public void shouldRunSettleContestWhenTwoPlayersJoin() {
		List<ContestDTO> playersList = setupForSettleContest();
		playersList.forEach(player -> {
			gameServiceImplMock.addPlayersFigures(player);
		});
		
		verify(challengeServiceMock, times(1)).challangePlayers(playersList);
		
	}
	
	
	@Test
	public void shouldGetResutlWhenTie() {
		
		when(challengeServiceMock.challangePlayers(anyList())).thenReturn("tie");
		
		setupForSettleContest().forEach(player -> {
			gameServiceImplMock.addPlayersFigures(player);
		});
		
		gameServiceImplMock.settleContest();
		ResultsDTO result = gameServiceImplMock.getResult();
		
		assertNotNull(result);
		assertEquals(result.getVerdict(), "tie");
		
	}
	
	@Test
	public void shouldGetResutlWhenNoTie() {
		
		when(challengeServiceMock.challangePlayers(anyList())).thenReturn("Tom");
		
		setupForSettleContest().forEach(player -> {
			gameServiceImplMock.addPlayersFigures(player);
		});
		
		gameServiceImplMock.settleContest();
		ResultsDTO result = gameServiceImplMock.getResult();
		
		assertNotNull(result);
		assertEquals(result.getVerdict(), "Tom");
		
	}
	
	public List<ContestDTO> setupForSettleContest() {
		
		ContestDTO firstPlayerDTO = new ContestDTO();
		ContestDTO secondPlayerDTO = new ContestDTO();
		
		firstPlayerDTO.setFigure("spock");
		firstPlayerDTO.setPlayer("Joe");
		secondPlayerDTO.setFigure("rock");
		secondPlayerDTO.setPlayer("Tom");
		
		List<ContestDTO> playersList = new ArrayList<>();
		playersList.add(firstPlayerDTO);
		playersList.add(secondPlayerDTO);
		
		return playersList;
		
	}

}
