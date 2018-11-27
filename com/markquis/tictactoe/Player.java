package com.markquis.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Player extends Controller {

	/**
	 * This class is responsible for the functionality of the players(user and computer).
	 * It allows the controller to construct a Player with a token.
	 */
	
	private String token;
	private static final String TOKEN_X = "X";
	private static final String TOKEN_O = "O";
	
	private static String getTokenX() {
		return TOKEN_X;
	}
		
	private static String getTokenO() {
		return TOKEN_O;
	}

	public String getToken() {
		return token;
	}
	
	public static List<String> getPlayerTokens() {
		List<String> tokens = new ArrayList<String>();
		tokens.add(TOKEN_X);
		tokens.add(TOKEN_O);
		return tokens;
	}
	
	public Player(String playerToken) {
		setPlayer(playerToken);
	}

	private void setPlayer(String playerToken) {
		if (playerToken != null) {
			if (playerToken.equals(TOKEN_X))
			this.token = playerToken;	
		} if (playerToken.equals(TOKEN_O)) {
			this.token = playerToken;
		}
	}
	
	public static Player getUserToken(String playerInput) {
		Player player = assignTokenToPlayer(playerInput);
		return player;
	}

	public static Player getComputerToken(Player userPlayer) {
		Player assignedComputerPlayer = assignComputerPlayerToken(userPlayer);
		return assignedComputerPlayer;
	}
	
	private static Player assignTokenToPlayer(String playerInput) {
		Player player;
		boolean userSelected = false;
		while (!userSelected) {
			if (playerInput != null && playerInput.equals("1")) {
				String playerToken = getTokenX();
				player = new Player(playerToken);
				return player;
			} else if (playerInput != null && playerInput.equals("2")) {
				String playerToken = getTokenO();
				player = new Player(playerToken);
				return player;
			} else {
				System.out.println("Your player selection was incorrect, please select 1 or 2.");
				continue;
			}
		}
		return null;
	}
	
	private static Player assignComputerPlayerToken(Player player) {
		Player computerPlayer;
		String playerX = getTokenX();
		String playerO = getTokenO();
		if (player.getToken().equals(playerX)) {
			computerPlayer = new Player(playerO);
			return computerPlayer;
		} else if (player.getToken().equals(playerO)) {
			computerPlayer = new Player(playerX);
			return computerPlayer;
		}
		return null;
	}
		
}
