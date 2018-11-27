package com.markquis.tictactoe;

public class Messages {
	
	public static void computerIsMoving() {
		System.out.println("Computer is moving, please wait...");
	}

	public static void boardIsFull() {
		System.out.println("the board is full, Game Over!");
	}

	public static void playerHasWon(String winningPlayer) {
		System.out.println("Player "+winningPlayer+" Wins!");
	}

	public static void gameOver() {
		System.out.println("Game Over");
	}

	public static void userPlayerSelection() {
		System.out.println(
				"Please select your desired player. Player 1 will be X. To select player 1 type 1 and press enter."+
		" "+"Player 2 will be O. To be player 2 type 2 and press enter.");
	}

	public static void userMovePrompt() {
		System.out.println("Please enter the placeholder value of the square you would like to move into.");

	}

	public static void startMenu() {
		System.out.println("Type Start and press Enter to begin a game.");
	}
}
