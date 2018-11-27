package com.markquis.tictactoe;

import java.util.concurrent.ThreadLocalRandom;

public class Board {

	/**
	 * This class is responsible for the functionality of the tic tac toe game
	 * board. Responsible for: Displaying the board Managing it's state - Which
	 * squares are empty, which are filled - Who filled it - Making sure it's a
	 * valid game board - Testing if the game board is full
	 */
	
	private static final String EMPTY_SQUARE = "";
	private static final int BOARD_SIZE = 3;
	private String[][] board;

	// make board size 4
	// adjust hardcoded values in the controller

	public Board() {
		this.board = new String[BOARD_SIZE][BOARD_SIZE];
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				setEmptySquare(row, column);
			}
		}
	}

	private void setEmptySquare(int row, int column) {
		if (board[row][column] == null) {
			board[row][column] = EMPTY_SQUARE;
		}
	}

	public void displayboard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				boolean isEmpty = isSquareEmpty(row, column);
				if (isEmpty) {
					System.out.print("|" + (row * BOARD_SIZE + column + 1) + "|");
				} else {
					System.out.print("|" + board[row][column] + "|");
				}
			}
			System.out.println("\n");
		}
	}

	public static int getLength() {
		return BOARD_SIZE;
	}

	public boolean isSquareEmpty(int row, int column) {
		return board[row][column].equals(EMPTY_SQUARE);
	}

	public void setPlayerToken(int row, int column, String player) {
		if (row < BOARD_SIZE && column < BOARD_SIZE) {
			board[row][column] = player;
		}
	}

	public void clearBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				board[row][column] = EMPTY_SQUARE;
			}
		}
	}

	public String getToken(int row, int column) {
		return board[row][column];
	}

	public boolean emptySquaresExist() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				boolean emptySquare = isSquareEmpty(row, column);
				if (emptySquare) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isBoardFull(Board board) {
		if (!board.emptySquaresExist()) {
			return board.emptySquaresExist();
		}
		return !board.emptySquaresExist();
	}

	public void addUserInputToBoard(String userMoveInput, Player player) {
		String userToken = player.getToken();
		boolean moveAddedToBoard = false;
		while (!moveAddedToBoard) {
			Integer boardSquareId = Integer.parseInt(userMoveInput);
			Position position = calculateRowAndColumn(boardSquareId);
			int row = position.getRow();
			int column = position.getColumn();
			if (isSquareEmpty(row, column)) {
				setPlayerToken(row, column, userToken);
				moveAddedToBoard = true;
			} else {
				System.out.println("You cannot move into that square. Please choose an new square.");
				userMoveInput = Controller.getUserInputReader();
			}
		}
	}

	
	public boolean addComputerInputToBoard(Player computerPlayer, Position position) {
		int row = position.getRow();
		int column = position.getColumn();
		String computerToken = computerPlayer.getToken();
		if (isSquareEmpty(row, column)) {
			setPlayerToken(row, column, computerToken);
			return true;
		} else {
			return false;
		}
	}

	private Position calculateRowAndColumn(Integer boardSquareId) {
		int row = (boardSquareId - 1) / board.length;
		int column = (boardSquareId - 1) % board.length;
		Position position = new Position(row, column);
		return position;
	}

	public Position getRandomEmptySquare() {
		boolean emptySpaceFound = false;
		while (!emptySpaceFound) {
			Position position = generateRandomSquare();
			return position;
		}
		return null;
	}

	private Position generateRandomSquare() {
		int row = ThreadLocalRandom.current().nextInt(0, board.length);
		int column = ThreadLocalRandom.current().nextInt(0, board.length);
		Position position = new Position(row, column);
		return position;
	}
}
