package com.game.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.game.dto.ContestDTO;
import com.game.service.GameService;

@Controller
public class GameController {
	
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	GameService gameService;
	
	@MessageMapping("/send/figure")
	@SendTo("/challenge/playerset")
	public List<String> reciveMessage(ContestDTO figure) {
		
		gameService.addPlayersFigures(figure);
		
		if(gameService.ifResultExist()) {
			sendResult();
		}
		
		return gameService.getPreperedPlayers();
	}
	
	@MessageMapping("/send/player")
	@SendTo("/challenge/players")
	public List<String> playerJoin(String player) {
		
		gameService.addNewPlayerToTheGame(player);
		
		return gameService.getPlayersList();
	}
	
	public void sendResult() {	
		simpMessagingTemplate.convertAndSend("/challenge/result", gameService.getResult());
	}
	
	@MessageMapping("/send/reset")
	@SendTo("/challenge/resetsettings")
	public boolean resetContest() {
		gameService.resetContest();
		
		return true;
	}
	
}
