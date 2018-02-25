package com.game.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.game.dto.ContestDTO;

public class ChallengeServiceTest {
	
	@InjectMocks
	ChallengeServiceImpl challengeServiceMock;
	
	private List<ContestDTO> players = new ArrayList<>();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldChallengeForTie() {
		players = setupPlayersForTie();
		String result = challengeServiceMock.challangePlayers(players);
		
		assertEquals(result, "tie");
	}
	
	// When first player win then result value is 'Joe'
	// When second player win then result value is 'Tom'
	@Test
	public void shouldRockWins() {
		players = setupPlayersForRockWinning();
		String result = challengeServiceMock.challangePlayers(players);
		
		assertEquals(result, "Joe");
	}
	
	@Test
	public void shouldSpockWins() {
		players = setupPlayersForSpockWinning();
		String result = challengeServiceMock.challangePlayers(players);
		
		assertEquals(result, "Tom");
	}
	
	public List<ContestDTO> setupPlayersForTie() {
		
		ContestDTO firstPlayerDTO = new ContestDTO();
		ContestDTO secondPlayerDTO = new ContestDTO();
		
		firstPlayerDTO.setFigure("spock");
		firstPlayerDTO.setPlayer("Joe");
		secondPlayerDTO.setFigure("spock");
		secondPlayerDTO.setPlayer("Tom");
		
		List<ContestDTO> playersList = new ArrayList<>();
		playersList.add(firstPlayerDTO);
		playersList.add(secondPlayerDTO);
		
		return playersList;
		
	}
	
	public List<ContestDTO> setupPlayersForRockWinning() {
		
		ContestDTO firstPlayerDTO = new ContestDTO();
		ContestDTO secondPlayerDTO = new ContestDTO();
		
		firstPlayerDTO.setFigure("rock");
		firstPlayerDTO.setPlayer("Joe");
		secondPlayerDTO.setFigure("lizard");
		secondPlayerDTO.setPlayer("Tom");
		
		List<ContestDTO> playersList = new ArrayList<>();
		playersList.add(firstPlayerDTO);
		playersList.add(secondPlayerDTO);
		
		return playersList;
		
	}
	
	public List<ContestDTO> setupPlayersForSpockWinning() {
		
		ContestDTO firstPlayerDTO = new ContestDTO();
		ContestDTO secondPlayerDTO = new ContestDTO();
		
		firstPlayerDTO.setFigure("scissors");
		firstPlayerDTO.setPlayer("Joe");
		secondPlayerDTO.setFigure("spock");
		secondPlayerDTO.setPlayer("Tom");
		
		List<ContestDTO> playersList = new ArrayList<>();
		playersList.add(firstPlayerDTO);
		playersList.add(secondPlayerDTO);
		
		return playersList;
		
	}

}
