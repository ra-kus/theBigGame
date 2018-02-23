package com.game.service;

import java.util.List;

import com.game.dto.ContestDTO;
import com.game.dto.ResultsDTO;

public interface GameService {
	
	public void addNewPlayerToTheGame(String playerNickname);
	public void addPlayersFigures(ContestDTO dto);
	
	public List<String> getPlayersList();
	public List<String> getPreperedPlayers();
	public ResultsDTO getResult();
	public boolean ifResultExist();
	
	public void resetContest();

}
