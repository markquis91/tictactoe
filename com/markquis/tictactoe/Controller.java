package com.markquis.tictactoe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

	/**
	 * This class is responsible for controlling the flow of the game. The controller is responsible for accepting
	 * user input, managing game moves and evaluating the gameboard for a winner.
	 * @param player
	 * @param moves
	 * @param board
	 */
		
	public static String getUserInputReader() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			boolean isDoneReading = false;
			while (!isDoneReading) {
				if (input != null) {
					if (!input.isEmpty()) {
						return input;
					} else {
						System.out.println("Your user input cannot be blank or null");
						input = br.readLine();
					}
				}
			}
		} catch (IOException e) {
			System.out.println("There is an error with the input stream, please try again.");
		}
		return null;
	}
	
	public void gameController() {
			Messages.startMenu();
			String input = getUserInputReader();
			switch (input.toLowerCase()) {
			case "start":
				Messages.userPlayerSelection();
				String playerToken = getUserInputReader();
				Player userToken = Player.getUserToken(playerToken);
				Player computerToken = Player.getComputerToken(userToken);
				boolean gameRunning = true;
				Board board = new Board();
				Evaluator evaluator = new Evaluator();
				do {
					board.displayboard();
					Messages.userMovePrompt();
					String userInputValue = getUserInputValue();
					board.addUserInputToBoard(userInputValue, userToken);
					board.displayboard();
					String winner = evaluator.checkForWinner(board);					
					if (winner != null) {
						Messages.playerHasWon(winner);
						board.displayboard();
						break;
					}
					boolean fullBoard = board.isBoardFull(board);
					if (fullBoard) {
						Messages.boardIsFull();
						break;
					}
					if (gameRunning) {
						Messages.computerIsMoving();
						Position computerMovePosition = board.getRandomEmptySquare();
						board.addComputerInputToBoard(computerToken, computerMovePosition);
						winner = evaluator.checkForWinner(board);						
						if (winner != null) {
							Messages.playerHasWon(winner);
							board.displayboard();
							break;
						}	
					} else if (gameRunning) {
						fullBoard = board.isBoardFull(board);
						if (fullBoard) {
							Messages.boardIsFull();
							break;
						}
					}
				} while (gameRunning);
				break;
			case "end":
				Messages.gameOver();
			}
	}
	
	private String getUserInputValue() {
		String userMoveInput = getUserInputReader();
		if (userMoveInput != null) {
				return userMoveInput;
		}
		return null;
	}
}
	