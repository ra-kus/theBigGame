package com.game.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.game.dto.ContestDTO;
import com.game.dto.ResultsDTO;

@Service
public class GameServiceImpl implements GameService{
	
	@Autowired
	ChallengeService challengeService;
	
	private String result;
	private String figure;
	
	
	//TODO: because of DB lack: lists, and map mimic the database
	List<String> playersList = new ArrayList<>();
	List<String> preperedPlayersList = new ArrayList<>();
	List<ContestDTO> players = new ArrayList<>();

	@Override
	public void addNewPlayerToTheGame(String playerNickname) {
		playersList.add(playerNickname);
	}
	
	@Override
	public void addPlayersFigures(ContestDTO dto) {
		preperedPlayersList.add(dto.getPlayer());
		players.add(dto);
		
		if(players.size() == 2) {
			settleContest();
		}
	}

	@Override
	public List<String> getPlayersList() {
		return playersList;
	}

	
	@Override
	public List<String> getPreperedPlayers() {
		return preperedPlayersList;
	}
	
	@Override
	public ResultsDTO getResult() {
		figure = null;
		ResultsDTO resDto = new ResultsDTO();
		
		if(!result.equals("tie") && !StringUtils.isEmpty(result)) {
			players.stream().forEach(ply -> {
				if(ply.getPlayer().equals(result)) {
					figure = ply.getFigure();
				}
			});
		}
		
		if(result.equals("tie") && !StringUtils.isEmpty(result)) {
			figure = players.get(0).getFigure();
		}
		
		resDto.setFigure(figure);
		resDto.setVerdict(result);
		resDto.setFirstPlayer(players.get(0).getPlayer());
		resDto.setFirstFigure(players.get(0).getFigure());
		resDto.setSecondPlayer(players.get(1).getPlayer());
		resDto.setSecondFigure(players.get(1).getFigure());
		
		return resDto;
	}
	
	@Override
	public void resetContest() {
		preperedPlayersList.clear();
		playersList.clear();
		players.clear();
		result = null;
		figure = null;
		
	}
	
	public void settleContest() {
		result = challengeService.challangePlayers(players);
	}

	@Override
	public boolean ifResultExist() {
		return result == null ? false : true;
	}

}
