package com.game.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.game.dto.ContestDTO;

@Service
public class ChallengeServiceImpl implements ChallengeService{
	
	String firstPlayerName;
	String firstPlayerFigure;
	String secondPlayerName;
	String secondPlayerFigure;

	@Override
	public String challangePlayers(List<ContestDTO> players) {
		
		firstPlayerName = players.get(0).getPlayer();
		firstPlayerFigure = players.get(0).getFigure();
		
		secondPlayerName = players.get(1).getPlayer();
		secondPlayerFigure = players.get(1).getFigure();

		int result = challange();
		
		if(result == 1) {
			return firstPlayerName;
		} else if (result == 2) {
			return secondPlayerName;
		}
		
		return "tie";
	}
	
	private Integer challange() {
		
		if(firstPlayerFigure.equals(secondPlayerFigure)) {
			return 0;
		}		
		
		switch (firstPlayerFigure) {
		case "rock":
			return ((secondPlayerFigure.equals("lizard") || secondPlayerFigure.equals("scissors")) ? 1 : 2);
		case "paper":
			return ((secondPlayerFigure.equals("rock") || secondPlayerFigure.equals("spock")) ? 1 : 2);
		case "scissors":
			return ((secondPlayerFigure.equals("paper") || secondPlayerFigure.equals("lizard")) ? 1 : 2);	
		case "lizard":
			return ((secondPlayerFigure.equals("spock") || secondPlayerFigure.equals("paper")) ? 1 : 2);
		case "spock":
			return ((secondPlayerFigure.equals("scissors") || secondPlayerFigure.equals("rock")) ? 1 : 2);	
		}
		
		return 2;
	}

}
